package com.example.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateFactory {
	/**
	 * Creates a singleton RestTemplate bean with all the necessary configuration.
	 * 
	 * @return a singleton RestTemplate bean
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
