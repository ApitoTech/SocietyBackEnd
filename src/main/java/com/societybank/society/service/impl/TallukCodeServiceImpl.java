package com.societybank.society.service.impl;

import com.societybank.society.dto.TallukCodeDto;
import com.societybank.society.entity.TallukCodeEntity;
import com.societybank.society.repository.TallukCodeRepository;
import com.societybank.society.service.TallukCodeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TallukCodeServiceImpl implements TallukCodeService {

    TallukCodeRepository tallukCodeRepository;

    @Autowired
    public TallukCodeServiceImpl(TallukCodeRepository tallukCodeRepository) {
        this.tallukCodeRepository = tallukCodeRepository;
    }

    @Override
    public TallukCodeDto save(TallukCodeDto tallukCodes) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TallukCodeEntity tallukCodeEntity= modelMapper.map(tallukCodes, TallukCodeEntity.class);
        tallukCodeRepository.save(tallukCodeEntity);
        TallukCodeDto returnData = modelMapper.map(tallukCodeEntity, TallukCodeDto.class);
        return returnData;
    }

    @Override
    public List<TallukCodeDto> findAll() {
        List<TallukCodeEntity> tallukCodes = tallukCodeRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<TallukCodeDto> tallukCodesList = Arrays.asList(modelMapper.map(tallukCodes, TallukCodeDto[].class));
        return tallukCodesList;
    }
}
