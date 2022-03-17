package com.societybank.society.service.impl;

import com.societybank.society.dto.InvoiceDto;
import com.societybank.society.entity.InvoiceEntity;
import com.societybank.society.repository.InvoiceRepository;
import com.societybank.society.service.InvoiceService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {


    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public InvoiceDto save(InvoiceDto invoice) {
        ModelMapper modelMapper= new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        InvoiceEntity invoiceEntity = modelMapper.map(invoice, InvoiceEntity.class);
        invoiceRepository.save(invoiceEntity);
        InvoiceDto returnData = modelMapper.map(invoiceEntity, InvoiceDto.class);
        return returnData;
    }

    @Override
    public List<InvoiceDto> findAll() {
        List<InvoiceEntity> invoice = invoiceRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<InvoiceDto> invoiceList = Arrays.asList(modelMapper.map(invoice, InvoiceDto[].class));
        return invoiceList;
    }
}
