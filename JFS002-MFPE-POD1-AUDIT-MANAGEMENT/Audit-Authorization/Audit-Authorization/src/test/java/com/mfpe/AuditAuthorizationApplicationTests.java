package com.mfpe;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import com.mfpe.controller.AuthController;
import com.mfpe.model.AuthenticationResponse;
import com.mfpe.model.ProjectManager;
import com.mfpe.model.ProjectManagerDetails;
import com.mfpe.service.JwtService;
import com.mfpe.service.ProjectManagerDetailsService;

@SpringBootTest
class AuditAuthorizationApplicationTests {
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	@Mock
	private ProjectManagerDetailsService projectManagerDetailsService;
	
	@Mock
	private JwtService jwtService;
	
	@InjectMocks
	private AuthController authController;
	
	@Test	
	public void validateJwt(){
		
		String jwtTokenHeader = "Bearer jj.ww.tt";
		AuthenticationResponse authenticationResponse = null;
		ResponseEntity<AuthenticationResponse> response = null;
		ProjectManagerDetails projectManagerDetails = null;
		ProjectManager projectManager = null;
		projectManager= new ProjectManager(1, "name1", "user1", "abcd1234");
		projectManagerDetails = new ProjectManagerDetails(projectManager);
		//authenticationResponse = new AuthenticationResponse("name1", true);
		String jwtToken = jwtTokenHeader.substring(7);
		String username = "user1";
		response = new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
		
		when(jwtService.extractUsername(jwtToken)).thenReturn(username);
		when(projectManagerDetailsService.loadUserByUsername(username))
							.thenReturn(projectManagerDetails);
		when(jwtService.validateToken(jwtToken, projectManagerDetails)).thenReturn(true);	// correct
		assertNotSame(response, authController.validateJwt(jwtTokenHeader));
	
	}

}
