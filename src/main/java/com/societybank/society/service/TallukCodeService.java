package com.societybank.society.service;

import com.societybank.society.dto.TallukCodeDto;

import java.util.List;

public interface TallukCodeService {
    TallukCodeDto save(TallukCodeDto tallukCodes);

    List<TallukCodeDto> findAll();

}
