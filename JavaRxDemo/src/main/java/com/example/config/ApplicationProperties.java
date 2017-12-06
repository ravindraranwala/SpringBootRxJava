package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties specific to JHipster.
 *
 * <p>
 * Properties are configured in the application.yml file.
 * </p>
 */
@Component
@ConfigurationProperties
public class ApplicationProperties {

	private final Security security = new Security();

	public Security getSecurity() {
		return security;
	}

	public static class Security {

		private final Authentication authentication = new Authentication();

		public Authentication getAuthentication() {
			return authentication;
		}

		public static class Authentication {
			private final Jwt jwt = new Jwt();

			public Jwt getJwt() {
				return jwt;
			}

			public static class Jwt {
				private String secret;
				private int tokenValidityInSeconds;

				public String getSecret() {
					return secret;
				}

				public void setSecret(String secret) {
					this.secret = secret;
				}

				public int getTokenValidityInSeconds() {
					return tokenValidityInSeconds;
				}

				public void setTokenValidityInSeconds(int tokenValidityInSeconds) {
					this.tokenValidityInSeconds = tokenValidityInSeconds;
				}

			}
		}
	}

	public String getJwtSecret() {
		return this.getSecurity().getAuthentication().getJwt().getSecret();
	}

	public int getTokenValidityInSeconds() {
		return this.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds();
	}
}
