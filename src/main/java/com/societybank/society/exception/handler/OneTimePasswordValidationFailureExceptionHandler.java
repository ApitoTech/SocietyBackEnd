package com.societybank.society.exception.handler;

import java.time.LocalDateTime;

import com.societybank.society.constant.ApiConstants;
import com.societybank.society.exception.OneTimePasswordValidationFailureException;
import org.json.JSONObject;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class OneTimePasswordValidationFailureExceptionHandler {

	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ResponseBody
	@ExceptionHandler(OneTimePasswordValidationFailureException.class)
	public ResponseEntity<?> otpValidationFailureHandler(OneTimePasswordValidationFailureException exception) {

		final var response = new JSONObject();
		response.put(ApiConstants.MESSAGE, ApiConstants.OTP_VALIDATION_FAILURE);
		response.put(ApiConstants.TIMESTAMP, LocalDateTime.now().toString());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response.toString());
	}

}
