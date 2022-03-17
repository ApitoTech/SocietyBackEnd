package com.societybank.society.controller;

import com.societybank.society.dto.InvoiceDto;
import com.societybank.society.dto.InvoiceResponseModel;
import com.societybank.society.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @RequestMapping
    public ResponseEntity<InvoiceResponseModel> getInvoices(){
        InvoiceResponseModel invoiceResponseModel = new InvoiceResponseModel();
        invoiceResponseModel.setInvoice(invoiceService.findAll());
        invoiceResponseModel.setDataCount(invoiceResponseModel.getInvoice().size());
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceResponseModel);
    }

    @RequestMapping("/save")
    public ResponseEntity<InvoiceDto> createInvoice(@RequestBody InvoiceDto invoiceDto){
        invoiceDto = invoiceService.save(invoiceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDto);
    }
}
