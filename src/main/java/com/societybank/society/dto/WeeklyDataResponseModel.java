package com.societybank.society.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WeeklyDataResponseModel {
    int dataCount = 0;
    List<WeeklyDataDto> weeklyData = new ArrayList<>();

}
