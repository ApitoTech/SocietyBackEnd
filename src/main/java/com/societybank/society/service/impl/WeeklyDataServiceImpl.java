package com.societybank.society.service.impl;

import com.societybank.society.dto.WeeklyDataDto;
import com.societybank.society.entity.WeeklyDataEntity;
import com.societybank.society.repository.WeeklyDataRepository;
import com.societybank.society.service.WeeklyDataService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WeeklyDataServiceImpl implements WeeklyDataService {

    WeeklyDataRepository weeklyDataRepository;

    @Autowired
    public WeeklyDataServiceImpl(WeeklyDataRepository weeklyDataRepository) {
        this.weeklyDataRepository = weeklyDataRepository;
    }

    @Override
    public WeeklyDataDto save(WeeklyDataDto weeklyData) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        WeeklyDataEntity weeklyDataEntity = modelMapper.map(weeklyData, WeeklyDataEntity.class);
        weeklyDataRepository.save(weeklyDataEntity);
        WeeklyDataDto returnData = modelMapper.map(weeklyDataEntity, WeeklyDataDto.class);
        return returnData;
    }

    @Override
    public List<WeeklyDataDto> findAll() {
        List<WeeklyDataEntity> weeklyDataEntities = weeklyDataRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<WeeklyDataDto> weeklyDataList = Arrays.asList(modelMapper.map(weeklyDataEntities, WeeklyDataDto[].class));
        return weeklyDataList;
    }
}
