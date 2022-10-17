package com.cognizant.auditchecklist.controller;


import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.cognizant.auditchecklist.feign.AuthorizationClient;
import com.cognizant.auditchecklist.service.AuditQuestionService;


@WebMvcTest(value = AuditChecklistController.class)
public class AuditChecklistControllerTest {
    
    @Autowired
	private MockMvc mockMvc;
    
    @MockBean
    AuditQuestionService auditQuestionService;

    @MockBean 
    AuthorizationClient authorizationClient;

    

    @Test
    public void testGetAuditQuestionsByAuditType() throws Exception{

        when(authorizationClient.authorizeRequest("jwt")).thenReturn(true);

        this.mockMvc.perform(get("/questions/Internal").header("Authorization", "jwt")).andExpect(status().isOk());

    }


    @Test
    public void testGetAuditQuestionsByAuditTypeException() throws Exception{

        when(authorizationClient.authorizeRequest("wrongtoken")).thenReturn(false);

        this.mockMvc.perform(get("/questions/Internal").header("Authorization", "wrongtoken"))
			.andExpect(status().isForbidden());

    }
}
