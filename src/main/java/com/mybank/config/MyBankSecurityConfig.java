package com.mybank.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MyBankSecurityConfig {

	/*@Bean
	InMemoryUserDetailsManager userDetailsService() {

		UserDetails admin = User.withUsername("sanjay").password("1234").authorities("admin")
				.build();
		UserDetails user = User.withUsername("mamta").password("1234").authorities("user")
				.build();
		
		return new InMemoryUserDetailsManager(admin, user);

	}*/
	
	/*@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}*/
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf((csrf) -> csrf.disable())
		.authorizeHttpRequests(
				(requests) -> requests.requestMatchers("/api/account", "/api/balance", "/api/loan", "/api/card")
						.authenticated() // APIs need to be authenticated -- can use RegEx also
		.requestMatchers("/api/notice", "/api/contact","/api/register").permitAll()) // does not needs authentication
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
	
	// Secures all endpoints invoked from form and basic security
	/*	@Bean
		SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
			System.out.println("Created own bean of security filter chain : CALLED AT APPLICATION STARTUP TIME");
			httpSecurity.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
			httpSecurity.formLogin(Customizer.withDefaults());
			httpSecurity.httpBasic(Customizer.withDefaults());
			return httpSecurity.build();

		}*/
	
	/*@Bean
	InMemoryUserDetailsManager userDetailsService() {

		UserDetails admin = User.withDefaultPasswordEncoder().username("sanjay").password("1234").authorities("admin")
				.build();
		UserDetails user = User.withDefaultPasswordEncoder().username("mamta").password("1234").authorities("user")
				.build();
		
		return new InMemoryUserDetailsManager(admin, user);

	}*/
}
