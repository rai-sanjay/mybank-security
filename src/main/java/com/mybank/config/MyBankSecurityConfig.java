package com.mybank.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.mybank.constant.AppConstant;
import com.mybank.filter.AuthoritiesLoggingAfterFilter;
import com.mybank.filter.CsrfCookieFilter;
import com.mybank.filter.RequestValidationBeforeFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class MyBankSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
		requestAttributeHandler.setCsrfRequestAttributeName("_csrf");
		/* Let Spring know that no SESSION to be managed */
		httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.cors((cors) -> cors.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						/* Let Browser know that no header to be accepted */
						config.setExposedHeaders(Arrays.asList(AppConstant.JWT_RESPONSE_HEADER));
						config.setMaxAge(3600L);
						return config;
					}
				}))
				// ignoring CSRF for public POST/PUT APIs
				.csrf((csrf) -> csrf.csrfTokenRequestHandler(requestAttributeHandler)
						.ignoringRequestMatchers("contact", "register")
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
				.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)

				.authorizeHttpRequests((requests) -> requests.requestMatchers("account").hasRole("USER")
						.requestMatchers("balance").hasAnyRole("USER", "ADMIN").requestMatchers("loan").hasRole("USER")
						.requestMatchers("card").hasRole("ADMIN")
						// ONLY Authentication
						.requestMatchers("user").authenticated()
						// APIs need to be authenticated -- can use RegEx also
						.requestMatchers("notice", "contact", "register").permitAll()) // does not needs authentication
				/* source of request - Rest & HTML form based login */
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());

		return httpSecurity.build();
	}
}
