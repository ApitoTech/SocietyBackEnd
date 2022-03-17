package com.societybank.society.controller;

import com.societybank.society.dto.WeeklyDataDto;
import com.societybank.society.dto.WeeklyDataResponseModel;
import com.societybank.society.service.WeeklyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weeklydata")
public class WeeklyDataController {

    private WeeklyDataService weeklyDataService;

    @Autowired
    public WeeklyDataController(WeeklyDataService weeklyDataService) {
        this.weeklyDataService = weeklyDataService;
    }

    @GetMapping
    public ResponseEntity<WeeklyDataResponseModel> getWeeklyData(){
        WeeklyDataResponseModel weeklyDataResponseModel = new WeeklyDataResponseModel();
        weeklyDataResponseModel.setWeeklyData(weeklyDataService.findAll());
        weeklyDataResponseModel.setDataCount(weeklyDataResponseModel.getWeeklyData().size());
        return ResponseEntity.status(HttpStatus.OK).body(weeklyDataResponseModel);
    }

    @PostMapping("/save")
    public ResponseEntity<WeeklyDataDto> createWeeklyData(@RequestBody WeeklyDataDto weeklyDataDto){
        weeklyDataDto = weeklyDataService.save(weeklyDataDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(weeklyDataDto);
    }
}
