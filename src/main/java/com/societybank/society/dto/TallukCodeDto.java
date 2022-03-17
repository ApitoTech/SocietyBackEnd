package com.societybank.society.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TallukCodeDto implements Serializable {
    private Integer code;
    private String talluk;
    private String district;
}
