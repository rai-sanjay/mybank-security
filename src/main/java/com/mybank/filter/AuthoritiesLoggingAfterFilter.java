package com.mybank.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class AuthoritiesLoggingAfterFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (null != authentication) {
			System.out.println("User " + authentication.getName() + " is successfully authenticated and "
					+ "has the authorities " + authentication.getAuthorities().toString());
		}
		chain.doFilter(request, response);
	}
}
/*
 * ROLES - [ROLE_ADMIN, ROLE_USER] User rai.abhyant@outlook.com is successfully
 * authenticated and has the authorities [ROLE_ADMIN, ROLE_USER]
 */
