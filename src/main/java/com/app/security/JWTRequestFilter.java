package com.app.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JWTUtils utils;
	
	@Autowired
	private UserDetailsService userDetails;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer")) {
			String jwtToken = header.substring(7);
			if(utils.validateJwtToken(jwtToken)) {
				String email = utils.getUserNameFromJwtToken(jwtToken);
				if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails user = userDetails.loadUserByUsername(email);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							user.getUsername(), user.getPassword(), user.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
}
