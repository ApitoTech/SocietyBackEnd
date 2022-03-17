package com.societybank.society.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InvoiceResponseModel {
    int dataCount = 0;
    List<InvoiceDto> invoice= new ArrayList<>();
}

