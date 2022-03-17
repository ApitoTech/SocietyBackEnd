package com.societybank.society.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class InvoiceDto implements Serializable {
    private Long id;
    private String subscritionStartDate;
    private Long plan;
}
