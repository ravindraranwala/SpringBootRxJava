package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.security.AuthoritiesConstants;
import com.pearson.nextgen.polarbearauthlib.security.filters.JWTAuthenticationFilter;
import com.pearson.nextgen.polarbearauthlib.security.providers.JWTAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final ApplicationProperties applicationProperties;

	public SecurityConfig(ApplicationProperties applicationProperties) {
		super();
		this.applicationProperties = applicationProperties;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/app/**/*.{js,html}", "/bower_components/**",
				"/i18n/**", "/content/**", "/swagger-ui/index.html", "/test/**", "/h2-console/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.csrf().disable().headers().frameOptions().disable().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("/api/**")
				.authenticated().antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN)
				.antMatchers("/swagger-resources/configuration/ui").permitAll().antMatchers("/health/**").permitAll()
				.and()
				.addFilterAfter(new JWTAuthenticationFilter(
						new JWTAuthenticationProvider(
								new ApplicationPropertiesDataAdapterJwt(this.applicationProperties)),
						null, new SimpleUrlAuthenticationFailureHandler()), BasicAuthenticationFilter.class);
	}
}
