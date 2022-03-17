package com.societybank.society.controller;

import com.societybank.society.constant.ApiConstants;
import com.societybank.society.dto.SubscriberLoginRequestDto;
import com.societybank.society.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/authentication")
public class OTPController {

    SubscriberService subscriberService;

    @Autowired
    OTPController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }

    @GetMapping("/otp/{mobile}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> otpGenerateHandler(
            @PathVariable(name = "mobile", required = true) final Long mobileNumber) throws ExecutionException {
        return subscriberService.generateOtp(mobileNumber);
    }

    @PutMapping("/validateOTP")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> validateOTPProceed(
            @Valid @RequestBody(required = true) final SubscriberLoginRequestDto subscriberLoginRequestDto)
            throws ExecutionException {
        return subscriberService.validateOTPProceed(subscriberLoginRequestDto);
    }

}
