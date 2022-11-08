package com.example.projekatglavni1.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.projekatglavni1.constants.SecurityConstants;
import com.example.projekatglavni1.utility.JwtTokenProvider;



@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter{

	private JwtTokenProvider jwtTokenProvider;
	
	public JwtAuthorizationFilter(JwtTokenProvider jwtTokenProvider) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		if(request.getMethod().equals(SecurityConstants.OPTIONS_HTTP_METHOD)) {
			response.setStatus(HttpStatus.OK.value());
		}else {
			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			if(authorizationHeader == null || !authorizationHeader.startsWith(SecurityConstants.JWT_TOKEN_HEADER)) {
				filterChain.doFilter(request, response);
				return;
			}
			
			String token = authorizationHeader.substring(SecurityConstants.JWT_TOKEN_HEADER.length());
			String username = jwtTokenProvider.getSubject(token);
			if(jwtTokenProvider.isTokenValid(username, token) && SecurityContextHolder.getContext().getAuthentication() == null) {
				List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);
				Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, request); 
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				SecurityContextHolder.clearContext();
			}
		}
		filterChain.doFilter(request, response);
		}
	}	
	


