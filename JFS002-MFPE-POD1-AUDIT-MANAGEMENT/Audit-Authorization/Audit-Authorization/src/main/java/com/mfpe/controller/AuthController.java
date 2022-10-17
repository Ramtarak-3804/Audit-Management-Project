package com.mfpe.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfpe.exception.ExcludeFromJacocoGeneratedReport;
import com.mfpe.model.AuthenticationRequest;
import com.mfpe.model.AuthenticationResponse;
import com.mfpe.model.JWTResponse;
import com.mfpe.model.ProjectManager;
import com.mfpe.model.ProjectManagerDetails;
import com.mfpe.service.JwtService;
import com.mfpe.service.ProjectManagerDetailsService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ProjectManagerDetailsService projectManagerDetailsService;
	
	@Autowired
	private JwtService jwtService;
	
	@ExcludeFromJacocoGeneratedReport
	@PostMapping("/authenticate")
	@ApiOperation(notes = "Authenticating the User-Credentials", value = "Get the JWT Token")
	public ResponseEntity<JWTResponse> generateJwt(@Valid @RequestBody AuthenticationRequest request){
		
		ResponseEntity<JWTResponse> response;
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
			final ProjectManagerDetails projectManagerDetails = projectManagerDetailsService.loadUserByUsername(request.getUserName());
		    ProjectManager projectManager = projectManagerDetailsService.getUserByUsername(request.getUserName());
			String managerName = projectManager.getName();
			final String jwt = jwtService.generateToken(projectManagerDetails);
			JWTResponse jwtResponse = new JWTResponse(jwt, managerName);
			response = new ResponseEntity<JWTResponse>(jwtResponse, HttpStatus.OK);
			logger.info("User Authentication Successfull");
			
		} catch (Exception e) {
			response = new ResponseEntity<JWTResponse>(new JWTResponse("INVALID_USER CREDENTIALS", null), HttpStatus.FORBIDDEN);
		}
		
		return response;
	}
	@ExcludeFromJacocoGeneratedReport
	@PostMapping("/authorize")
	@ApiOperation(notes = "Validating the JWT Token in Authorization Header", value = "Validating for Every Request")
	public boolean validateJwt(@RequestHeader("Authorization") String jwt){
		
		jwt = jwt.substring(7);
		// Checking JWT Token is Proper or not
		final ProjectManagerDetails projectManagerDetails = projectManagerDetailsService
															.loadUserByUsername(jwtService.extractUsername(jwt));
		// Validating the JWT
		try {
			if(jwtService.validateToken(jwt, projectManagerDetails)) {
				logger.info("Validation Successfull Response Sent");
				return true;
			}
			else {
				logger.error("JWT Token validation failed!");
				return false;
			}
		}catch (Exception e) {
			logger.error("Exception occured whil validating JWT");
			return false;
		}
		
	}
	
}
