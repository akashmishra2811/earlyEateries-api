package com.example.earlyEateries.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	
	  final JwtService jwtService ;
	final UserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
	     String jwt;
	     String userEmail;
		 if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			 filterChain.doFilter(request, response);
			 return;
		 }
		jwt = authHeader.substring(7);
	 userEmail =jwtService.extractUserName(jwt);
	 if(userEmail != null &&  SecurityContextHolder.getContext().getAuthentication() ==null) {
		 
		 UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
		 
		 if(jwtService.isTokenValid(jwt, userDetails)) {
			 
			 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userEmail,null, userDetails.getAuthorities());
			 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			 SecurityContextHolder.getContext().setAuthentication(authToken);
		 }
		filterChain.doFilter(request, response);
		 
	 }
	}

}
