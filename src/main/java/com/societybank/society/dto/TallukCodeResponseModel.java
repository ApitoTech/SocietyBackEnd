package com.societybank.society.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TallukCodeResponseModel {
    int dataCount = 0;
    List<TallukCodeDto> tallukCodes = new ArrayList<>();
}
