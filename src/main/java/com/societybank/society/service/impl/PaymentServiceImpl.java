package com.societybank.society.service.impl;

import com.razorpay.Order;
import com.societybank.society.entity.PaymentEntity;
import com.societybank.society.entity.SubscriberEntity;
import com.societybank.society.repository.PaymentRepository;
import com.societybank.society.service.PaymentService;
import com.societybank.society.service.SubscriberService;
import com.societybank.society.utils.Signature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, SubscriberService subscriberService) {
        this.paymentRepository = paymentRepository;
        this.subscriberService = subscriberService;
    }

    private PaymentRepository paymentRepository;

    private SubscriberService subscriberService;


    @Transactional
    public PaymentEntity savePayment(final String razorpayOrderId, final Long subSubscriberId, Order order) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAmount((Integer) order.get("amount"));
        paymentEntity.setOrderId((String) order.get("id"));
        paymentEntity.setRazorpayPaymentId("");
        paymentEntity.setRazorpaySignature("");
        paymentEntity.setReceiptNo((String) order.get("receipt"));
        paymentEntity.setRazorpayOrderId(razorpayOrderId);
        paymentEntity.setStatus((String) order.get("status"));
        paymentEntity.setOrder(order.toString());

        paymentEntity.setCreatedDate(new Date());

        SubscriberEntity subscriberEntity = subscriberService.findById(subSubscriberId).get();
        paymentEntity.setSubscriber(subscriberEntity);
        return paymentRepository.save(paymentEntity);
    }

    @Transactional
       public String validateAndUpdateOrder(final String razorpayOrderId, final String razorpayPaymentId, final String razorpaySignature, final String secret) {
            String errorMsg = null;
        try {
            PaymentEntity paymentEntity = paymentRepository.findByRazorpayOrderId(razorpayOrderId);
            String generatedSignature = Signature.calculateRFC2104HMAC(paymentEntity.getRazorpayOrderId() + "|" + razorpayPaymentId, secret);
            if (generatedSignature.equals(razorpaySignature)) {
                paymentEntity.setRazorpayOrderId(razorpayOrderId);
                paymentEntity.setRazorpayPaymentId(razorpayPaymentId);
                paymentEntity.setRazorpaySignature(razorpaySignature);
                paymentRepository.save(paymentEntity);
            } else {
                errorMsg = "Payment validation failed: Signature doesn't match";
            }
        } catch (Exception e) {
            log.error("Payment validation failed", e);
            errorMsg = e.getMessage();
        }
        return errorMsg;
    }



}
