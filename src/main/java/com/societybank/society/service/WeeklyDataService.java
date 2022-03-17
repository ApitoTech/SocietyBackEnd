package com.societybank.society.service;

import com.societybank.society.dto.WeeklyDataDto;

import java.util.List;

public interface WeeklyDataService {
    WeeklyDataDto save(WeeklyDataDto weeklyData);

    List<WeeklyDataDto> findAll();


}
