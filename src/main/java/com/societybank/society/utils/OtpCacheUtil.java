package com.societybank.society.utils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.societybank.society.configuration.properties.OneTimePasswordConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(OneTimePasswordConfigurationProperties.class)
public class OtpCacheUtil {

	private final OneTimePasswordConfigurationProperties oneTimePasswordConfigurationProperties;

	@Bean
	public LoadingCache<Long, Integer> loadingCache() {
		final var expirationMinutes = oneTimePasswordConfigurationProperties.getOtp().getExpirationMinutes();
		return CacheBuilder.newBuilder().expireAfterWrite(expirationMinutes, TimeUnit.MINUTES)
				.build(new CacheLoader<>() {
					public Integer load(Long key) {
						return 0;
					}
				});
	}


}
