package com.mfpe;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mfpe.exception.ProjectManagerNotFoundException;
import com.mfpe.model.ProjectManager;
import com.mfpe.model.ProjectManagerDetails;
import com.mfpe.service.ProjectManagerDetailsService;
import com.mfpe.service.ProjectManagerService;

@SpringBootTest
public class ServiceTest {

	@Mock
	private ProjectManagerService projectManagerService;
	
	@InjectMocks
	private ProjectManagerDetailsService projectManagerdetailsService;
	
	@Test
	public void loadUserByUsernameTest() throws UsernameNotFoundException {
		String username1 = "user1";
		ProjectManager projectManager = null;
		projectManager = new ProjectManager();
		projectManager.setId(1);
		projectManager.setName("user1");
		projectManager.setPassword("pass123");
		
		when(projectManagerService.getProjectManagerByUserName(username1)).thenReturn(projectManager);
		assertEquals(new ProjectManagerDetails(projectManager).getName(), projectManagerdetailsService.loadUserByUsername(username1).getName());
		
		projectManager = null;
		String username2 = "Rajesh";
		when(projectManagerService.getProjectManagerByUserName(username2)).thenReturn(projectManager);
		assertEquals(null, projectManagerdetailsService.loadUserByUsername(username2));
		
	}
	
	@Test
	public void testException() {
		assertEquals("Project Manager is Not Found",(new ProjectManagerNotFoundException("Project Manager is Not Found")).getMessage());
	}
	
}
