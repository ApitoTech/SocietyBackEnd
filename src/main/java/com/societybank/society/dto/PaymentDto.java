package com.societybank.society.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class PaymentDto implements Serializable {
    private Long id;
    private Integer amount;
    private Instant createdDate;
    private String orderId;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String receiptNo;
    private String status;
}
