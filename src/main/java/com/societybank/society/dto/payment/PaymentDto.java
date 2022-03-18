package com.societybank.society.dto.payment;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class PaymentDto implements Serializable {
    private String amount;
    private Long userId;
}
