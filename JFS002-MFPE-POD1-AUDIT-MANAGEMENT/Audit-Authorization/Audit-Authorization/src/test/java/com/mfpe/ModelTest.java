package com.mfpe;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import com.mfpe.model.AuthenticationResponse;
import com.mfpe.model.ProjectManagerDetails;

@SpringBootTest
public class ModelTest {

	@Test
	public void testGetterAndSetter() {
		AuthenticationResponse authResponse = new AuthenticationResponse();
		//authResponse.setName("rajesh");
		authResponse.setValid(false);
		//assertEquals("rajesh", authResponse.getName());
		assertEquals(false, authResponse.isValid());
	}
	
	@Test
	public void testGetPassword() {
		ProjectManagerDetails obj = new ProjectManagerDetails();
		assertEquals(null,obj.getPassword());
	}

	@Test
	public void testGetUsername() {
		ProjectManagerDetails obj = new ProjectManagerDetails();
		assertEquals(null,obj.getUsername());
	}
	
}
