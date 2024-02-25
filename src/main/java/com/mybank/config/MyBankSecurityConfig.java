package com.mybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MyBankSecurityConfig {

	// Secures all endpoints invoked from form and basic security
/*	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		System.out.println("Created own bean of security filter chain : CALLED AT APPLICATION STARTUP TIME");
		httpSecurity.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
		httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.httpBasic(Customizer.withDefaults());
		return httpSecurity.build();

	}*/
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.authorizeHttpRequests(
				(requests) -> requests.requestMatchers("/api/account", "/api/balance", "/api/loan", "/api/card")
						.authenticated() // APIs need to be authenticated -- can use RegEx also
		.requestMatchers("/api/notice", "/api/contact").permitAll()) // does not needs authentication
		.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults()); // source of request - Rest and HTML form based login

		return httpSecurity.build();

	}

	//Deny All
			/*@Bean
			SecurityFilterChain filterSecurityChain(HttpSecurity httpSecurity) throws Exception {

				httpSecurity.authorizeHttpRequests((request) -> request.anyRequest().denyAll())
						.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
				return httpSecurity.build();

			}*/
			
	//Permit All
			
			/*
			@Bean
			SecurityFilterChain filterSecurityChain(HttpSecurity httpSecurity) throws Exception {

				httpSecurity.authorizeHttpRequests((request) -> request.anyRequest().permitAll())
						.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
				return httpSecurity.build();

			}
			*/
}
