package com.mybank.config;

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

import com.mybank.filter.CsrfCookieFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class MyBankSecurityConfig {

	/*
	 * @Bean InMemoryUserDetailsManager userDetailsService() {
	 * 
	 * UserDetails admin =
	 * User.withUsername("sanjay").password("1234").authorities("admin") .build();
	 * UserDetails user =
	 * User.withUsername("mamta").password("1234").authorities("user") .build();
	 * 
	 * return new InMemoryUserDetailsManager(admin, user);
	 * 
	 * }
	 */

	/*
	 * @Bean public UserDetailsService userDetailsService(DataSource dataSource) {
	 * return new JdbcUserDetailsManager(dataSource); }
	 */

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		CsrfTokenRequestAttributeHandler requestAttributeHandler = new CsrfTokenRequestAttributeHandler();
		requestAttributeHandler.setCsrfRequestAttributeName("_csrf");

		httpSecurity
		//56,57 Only required for web applications( Angular/React+Java)
		.securityContext((securityContext) -> securityContext.requireExplicitSave(false))
		.sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		
		.cors((cors) -> cors.configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setMaxAge(3600L);
				return config;
			}
		}))
		// ignoring CSRF for public POST/PUT APIs
				.csrf((csrf) -> csrf.csrfTokenRequestHandler(requestAttributeHandler)
						.ignoringRequestMatchers("/api/contact", "/api/register")
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)

				
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/api/account").hasAuthority("VIEW ACCOUNT")
						.requestMatchers("/api/balance").hasAnyAuthority("VIEW ACCOUNT","VIEW BALANCE")
						.requestMatchers("/api/loan").hasAuthority("VIEW LOAN")
						.requestMatchers("/api/card").hasAuthority("VIEW CARD")
						//ONLY Authentication
						.requestMatchers("/api/user").authenticated() 
						// APIs need to be authenticated -- can use RegEx also
						.requestMatchers("/api/notice", "/api/contact", "/api/register").permitAll()) // does not needs
																										// authentication
				.formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults()); // source of request - Rest
																							// and HTML form based login

		return httpSecurity.build();

	}

	// Deny All
	/*
	 * @Bean SecurityFilterChain filterSecurityChain(HttpSecurity httpSecurity)
	 * throws Exception {
	 * 
	 * httpSecurity.authorizeHttpRequests((request) ->
	 * request.anyRequest().denyAll())
	 * .formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
	 * return httpSecurity.build();
	 * 
	 * }
	 */

	// Permit All

	/*
	 * @Bean SecurityFilterChain filterSecurityChain(HttpSecurity httpSecurity)
	 * throws Exception {
	 * 
	 * httpSecurity.authorizeHttpRequests((request) ->
	 * request.anyRequest().permitAll())
	 * .formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
	 * return httpSecurity.build();
	 * 
	 * }
	 */

	// Secures all endpoints invoked from form and basic security
	/*
	 * @Bean SecurityFilterChain defaultSecurityFilterChain(HttpSecurity
	 * httpSecurity) throws Exception { System.out.
	 * println("Created own bean of security filter chain : CALLED AT APPLICATION STARTUP TIME"
	 * ); httpSecurity.authorizeHttpRequests((requests) ->
	 * requests.anyRequest().authenticated());
	 * httpSecurity.formLogin(Customizer.withDefaults());
	 * httpSecurity.httpBasic(Customizer.withDefaults()); return
	 * httpSecurity.build();
	 * 
	 * }
	 */

	/*
	 * @Bean InMemoryUserDetailsManager userDetailsService() {
	 * 
	 * UserDetails admin =
	 * User.withDefaultPasswordEncoder().username("sanjay").password("1234").
	 * authorities("admin") .build(); UserDetails user =
	 * User.withDefaultPasswordEncoder().username("mamta").password("1234").
	 * authorities("user") .build();
	 * 
	 * return new InMemoryUserDetailsManager(admin, user);
	 * 
	 * }
	 */
}
