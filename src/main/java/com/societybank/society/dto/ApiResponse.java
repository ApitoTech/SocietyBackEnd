package com.societybank.society.dto;

import lombok.Value;

@Value
public class ApiResponse {
	private Boolean success;
	private String message;
}