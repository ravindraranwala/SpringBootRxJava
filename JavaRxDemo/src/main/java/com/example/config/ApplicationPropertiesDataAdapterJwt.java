package com.example.config;

import com.pearson.nextgen.polarbearauthlib.security.providers.JWTAuthenticationProviderData;

/**
 * Adapts {@link ApplicationProperties} configuration data into the format
 * expected by the {@link TokenProvider}
 * 
 *
 */
public class ApplicationPropertiesDataAdapterJwt implements JWTAuthenticationProviderData {
	private final ApplicationProperties applicationProperties;

	/**
	 * Constructs an instance of {@link ApplicationPropertiesDataAdapterJwt} with
	 * the {@link ApplicationProperties} config data passed in.
	 * 
	 * @param applicationProperties
	 *            {@link ApplicationProperties} configuration data passed in.
	 */
	public ApplicationPropertiesDataAdapterJwt(ApplicationProperties applicationProperties) {
		super();
		this.applicationProperties = applicationProperties;
	}

	@Override
	public String getSecret() {
		return this.applicationProperties.getJwtSecret();
	}

	@Override
	public long getTokenValidityInSeconds() {
		return this.applicationProperties.getTokenValidityInSeconds();
	}

	@Override
	public long getTokenValidityInSecondsForRememberMe() {
		return 0;
	}
}
