package com.societybank.society.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.societybank.society")
public class OneTimePasswordConfigurationProperties {

	private OTP otp = new OTP();

	@Data
	public class OTP {
		private Integer expirationMinutes;
	}

}
