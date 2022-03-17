package com.societybank.society.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class WeeklyDataDto implements Serializable {
    private Integer weekYear;
    private LocalDate genratedDate;
    private Integer complementaryCount;
    private Integer total;
}
