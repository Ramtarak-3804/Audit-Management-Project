package com.cognizant.auditbenchmark.controller;

import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cognizant.auditbenchmark.feign.AuthorizationClient;
import com.cognizant.auditbenchmark.service.AuditBenchmarkService;

@WebMvcTest(value = AuditBenchmarkController.class)
public class AuditBenchmarkControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AuditBenchmarkService auditBenchmarkService;

    @MockBean
    AuthorizationClient authorizationClient;


    @Test
    void testGetAuditBenchmarkAuthorization() throws Exception{

        when(authorizationClient.authorizeRequest("jwt")).thenReturn(true);

        this.mockMvc.perform(get("/benchmark")
        .header("Authorization", "jwt"))
        .andExpect(status().isOk());
    }

    @Test
    void testGetAuditBenchmark_badRequest() throws Exception{

        when(authorizationClient.authorizeRequest("jwt")).thenReturn(true);

        this.mockMvc.perform(get("/benchmark")
        .header("Authorization", "jwtt"))
        .andExpect(status().isForbidden());
    }


}
