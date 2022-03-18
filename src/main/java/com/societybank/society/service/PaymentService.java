package com.societybank.society.service;

import com.razorpay.Order;
import com.societybank.society.entity.PaymentEntity;

public interface PaymentService {

    PaymentEntity savePayment(final String razorpayOrderId, final Long subSubscriberId, Order order);

    String validateAndUpdateOrder(final String razorpayOrderId, final String razorpayPaymentId, final String razorpaySignature, final String secret);

}
