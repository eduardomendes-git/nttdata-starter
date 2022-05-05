package com.edu.publiclibrary.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;

/**
 * @author	esilvmen
 * @date	03/05/2022
 *
 */
@EnableWebSecurity
public class OidcSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		// Disables the Cross Site Request Forgery (CSRF) protection
		// TODO Check if we can do without this
		//httpSecurity.csrf().disable();
		
		httpSecurity
			.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
			.oauth2Login(withDefaults());
		
		// Disables the security configuration, if needed
		//httpSecurity.httpBasic().disable();
	}
	
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {
		
		return new InMemoryClientRegistrationRepository(azureClientRegistration());
	}
	
	private ClientRegistration azureClientRegistration() {
		
		return ClientRegistration.withRegistrationId("public-library")
					.clientId("7919af5b-8348-4efb-a4aa-dd6c98900dcf")
					.clientSecret("uTB8Q~doyCKTtSJxzEPI~ge58fDybmC0swnn3cU0")
					.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
					.scope("openid", "profile", "email")
					.authorizationUri("https://login.microsoftonline.com/dd9338f0-1ece-4b7f-bfd1-c9088f40f30f/oauth2/v2.0/authorize")
					.redirectUri("http://localhost:8080/login/oauth2/code/public-library")
					.tokenUri("https://login.microsoftonline.com/dd9338f0-1ece-4b7f-bfd1-c9088f40f30f/oauth2/v2.0/token")
					.jwkSetUri("https://login.microsoftonline.com/dd9338f0-1ece-4b7f-bfd1-c9088f40f30f/discovery/keys?appid=7919af5b-8348-4efb-a4aa-dd6c98900dcf")
					.build();
	}
	
}
