package com.cognizant.auditseverity.controller;

import static org.mockito.Mockito.when;


import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cognizant.auditseverity.feign.AuthorizationClient;
import com.cognizant.auditseverity.feign.BenchmarkClient;
import com.cognizant.auditseverity.model.AuditBenchmark;
import com.cognizant.auditseverity.model.AuditDetail;
import com.cognizant.auditseverity.model.AuditRequest;
import com.cognizant.auditseverity.model.AuditResponse;
import com.cognizant.auditseverity.model.AuditType;
import com.cognizant.auditseverity.model.Question;
import com.cognizant.auditseverity.service.AuditSeverityService;
import com.fasterxml.jackson.databind.ObjectMapper;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



@WebMvcTest
public class AuditSeverityControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuditSeverityService auditSeverityService;

    @MockBean
    private AuthorizationClient authorizationClient;

    @MockBean
    private BenchmarkClient benchmarkClient;


    @Autowired
    private ObjectMapper objectMapper;

    private AuditRequest auditRequest;
    private AuditResponse auditResponse;
    private AuditDetail auditDetail;
    private AuditBenchmark auditBenchmark1;
    private AuditBenchmark auditBenchmark2;

    @BeforeEach
    public void setup(){
        Question question1 = Question.builder().question("question 1").auditType(AuditType.Internal).response("yes").build();
        Question question2 = Question.builder().question("question 2").auditType(AuditType.Internal).response("yes").build();

       

        auditDetail = AuditDetail.builder().auditDate("25-09-2000").auditQuestions(Arrays.asList(question1,question2)).build();

        auditRequest = AuditRequest.builder()
                                    .projectName("project 1")
                                    .managerName("manager 1")
                                    .auditDetail(auditDetail).build();

        auditResponse = AuditResponse.builder()
                                        .projectExecutionStatus("Green")
                                        .remedialActionDuration("No Action Needed").build();

        auditBenchmark1 = AuditBenchmark.builder()
                                        .auditType(AuditType.Internal)
                                        .acceptableNos(3).build();

        auditBenchmark2 = AuditBenchmark.builder()
                                        .auditType(AuditType.SOX)
                                        .acceptableNos(1).build();
    }

    @Test
    public void test_get_auditSeverity_correctResponse() throws Exception{


        when(auditSeverityService.getAuditSeverityResponse(auditRequest, auditBenchmark1)).thenReturn(auditResponse);

        when(benchmarkClient.getAuditBenchmark("jwt")).thenReturn(Arrays.asList(auditBenchmark1,auditBenchmark2));

        when(authorizationClient.authorizeRequest("jwt")).thenReturn(true);

        this.mockMvc.perform(post("/severity").header("Authorization", "jwt").contentType(MediaType.APPLICATION_JSON)
                                                            .content(objectMapper.writeValueAsString(auditRequest))).andExpect(status().isOk());

                                    
    }

    @Test
    public void test_get_auditSeverity_NotAllowed() throws Exception{
        when(authorizationClient.authorizeRequest("fakejwt")).thenReturn(false);

        this.mockMvc.perform(post("/severity").header("Authorization", "jwt").contentType(MediaType.APPLICATION_JSON)
                                                            .content(objectMapper.writeValueAsString(auditRequest))).andExpect(status().isForbidden());
    }

}