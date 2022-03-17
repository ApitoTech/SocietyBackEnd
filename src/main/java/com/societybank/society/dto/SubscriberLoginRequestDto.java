package com.societybank.society.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@JacksonStdImpl
public class SubscriberLoginRequestDto {

    @NotBlank(message = "Mobile must not be empty")
    private final Long mobile;

    @NotBlank(message = "OTP Must not be blank")
    private final Integer otp;

}
