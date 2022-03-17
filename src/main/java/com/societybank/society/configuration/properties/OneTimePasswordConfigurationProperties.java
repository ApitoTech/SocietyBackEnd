package com.societybank.society.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
public class OneTimePasswordConfigurationProperties {

	private OTP otp = new OTP();

	@Data
	public class OTP {
		private Integer expirationMinutes;
	}

}
