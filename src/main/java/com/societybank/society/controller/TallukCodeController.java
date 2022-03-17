package com.societybank.society.controller;

import com.societybank.society.dto.TallukCodeDto;
import com.societybank.society.dto.TallukCodeResponseModel;
import com.societybank.society.service.TallukCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tallukcode")
public class TallukCodeController {

    private TallukCodeService tallukCodeService;

    @Autowired
    public TallukCodeController(TallukCodeService tallukCodeService) {
        this.tallukCodeService = tallukCodeService;
    }

    @GetMapping
    public ResponseEntity<TallukCodeResponseModel> getTallukCodes(){
        TallukCodeResponseModel tallukCodeResponseModel = new TallukCodeResponseModel();
        tallukCodeResponseModel.setTallukCodes(tallukCodeService.findAll());
        tallukCodeResponseModel.setDataCount(tallukCodeResponseModel.getTallukCodes().size());
        return ResponseEntity.status(HttpStatus.OK).body(tallukCodeResponseModel);
    }

    @PostMapping("/save")
    public ResponseEntity<TallukCodeDto> createTallukCodes(@RequestBody TallukCodeDto tallukCodeDto){
        tallukCodeDto = tallukCodeService.save(tallukCodeDto);
        return null;
    }

}
