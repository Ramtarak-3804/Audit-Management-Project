package com.mfpe.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mfpe.exception.ProjectManagerNotFoundException;
import com.mfpe.model.ProjectManagerDetails;
import com.mfpe.service.JwtService;
import com.mfpe.service.ProjectManagerDetailsService;

import io.swagger.annotations.ApiOperation;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private ProjectManagerDetailsService projectManagerDetailsService;

	@Autowired
	private JwtService jwtService;
	
	Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

	
	@Override
	@ApiOperation(notes = "Filtering Every Request and response", value = "Filtering the request and response")
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException, ProjectManagerNotFoundException {
		
		final String jwtRequestHeader = request.getHeader("Authorization");
		
		String jwt = null, username = null;
		if (jwtRequestHeader != null && jwtRequestHeader.startsWith("Bearer ")) {
			jwt = jwtRequestHeader.substring(7);
			try {
				username = jwtService.extractUsername(jwt);
			} catch (Exception e) {
				
			}
		} else { }
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			ProjectManagerDetails projectManagerDetails = projectManagerDetailsService.loadUserByUsername(username);
			if (jwtService.validateToken(jwt, projectManagerDetails)) {
				UsernamePasswordAuthenticationToken 
					usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
															projectManagerDetails, null, 
															projectManagerDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			else {
				logger.error("Validation failed for JWT Token ");
			}
		}
		else {
			
		}
		filterChain.doFilter(request, response);
	}
}
