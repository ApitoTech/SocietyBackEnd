package com.societybank.society.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.societybank.society.configuration.properties.RazorPayClientConfig;
import com.societybank.society.dto.ApiResponse;
import com.societybank.society.dto.payment.OrderResponse;
import com.societybank.society.dto.payment.PaymentDto;
import com.societybank.society.dto.payment.PaymentResponse;
import com.societybank.society.service.PaymentService;
import com.societybank.society.service.SubscriberService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Chinna
 */
@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PaymentOrderController {

    private RazorpayClient client;

    private RazorPayClientConfig razorPayClientConfig;

    private PaymentService paymentService;

    private SubscriberService subscriberService;


    @Autowired
    public PaymentOrderController(RazorPayClientConfig razorpayClientConfig, PaymentService paymentService, SubscriberService subscriberService) throws RazorpayException {
        this.razorPayClientConfig = razorpayClientConfig;
        this.client = new RazorpayClient(razorpayClientConfig.getKey(), razorpayClientConfig.getSecret());
        this.paymentService = paymentService;
        this.subscriberService = subscriberService;
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody PaymentDto orderRequest) {
        OrderResponse razorPay = null;
        try {
           // String amountInPaise = convertRupeeToPaise(orderRequest.getAmount());
        	String amountInPaise = convertRupeeToPaise("300");
            Order order = createRazorPayOrder(amountInPaise);
            razorPay = getOrderResponse((String) order.get("id"), amountInPaise);
            paymentService.savePayment(razorPay.getRazorpayOrderId(), orderRequest.getUserId() , order);
        } catch (RazorpayException e) {
            log.error("Exception while create payment order", e);
            return new ResponseEntity<>(new ApiResponse(false, "Error while create payment order: " + e.getMessage()), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(razorPay);
    }

    @PutMapping("/order")
    public ResponseEntity<?> updateOrder(@RequestBody PaymentResponse paymentResponse) {
        String errorMsg = paymentService.validateAndUpdateOrder(paymentResponse.getRazorpayOrderId(), paymentResponse.getRazorpayPaymentId(), paymentResponse.getRazorpaySignature(),
                razorPayClientConfig.getSecret());
        if (errorMsg != null) {
            return new ResponseEntity<>(new ApiResponse(false, errorMsg), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new ApiResponse(true, paymentResponse.getRazorpayPaymentId()));
    }

    private OrderResponse getOrderResponse(String orderId, String amountInPaise) {
        OrderResponse razorPay = new OrderResponse();
        razorPay.setApplicationFee(amountInPaise);
        razorPay.setRazorpayOrderId(orderId);
        razorPay.setSecretKey(razorPayClientConfig.getKey());
        return razorPay;
    }

    private Order createRazorPayOrder(String amount) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", amount);
        options.put("currency", "INR");
        options.put("receipt", "txn_123457");
        return client.Orders.create(options);
    }

    private String convertRupeeToPaise(String paise) {
        BigDecimal b = new BigDecimal(paise);
        BigDecimal value = b.multiply(new BigDecimal("100"));
        return value.setScale(0, RoundingMode.UP).toString();
    }
}
