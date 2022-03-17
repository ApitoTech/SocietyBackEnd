package com.societybank.society.service;

import com.societybank.society.dto.InvoiceDto;

import java.util.List;

public interface InvoiceService {
    InvoiceDto save(InvoiceDto invoice);

    List<InvoiceDto> findAll();
}
