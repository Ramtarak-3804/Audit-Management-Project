package com.cognizant.auditseverity.service;


import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.auditseverity.model.AuditBenchmark;
import com.cognizant.auditseverity.model.AuditDetail;
import com.cognizant.auditseverity.model.AuditRequest;
import com.cognizant.auditseverity.model.AuditResponse;
import com.cognizant.auditseverity.model.AuditType;
import com.cognizant.auditseverity.model.Question;

@ExtendWith(MockitoExtension.class)
public class AuditSeverityServiceTest {

    @InjectMocks
    AuditSeverityService auditSeverityService;

    private AuditRequest auditRequest1;
    private AuditRequest auditRequest2;
    private AuditRequest auditRequest3;
    private AuditResponse auditResponseGreen;
    private AuditResponse auditResponseRedInternal;
    private AuditResponse auditResponseRedSOX;
    private AuditDetail auditDetail1;
    private AuditDetail auditDetail2;
    private AuditDetail auditDetail3;
    private AuditBenchmark auditBenchmark1;
    private AuditBenchmark auditBenchmark2;

    @BeforeEach
    public void setup(){
        Question question1 = Question.builder().question("question 1").auditType(AuditType.Internal).response("yes").build();
        Question question2 = Question.builder().question("question 2").auditType(AuditType.Internal).response("no").build();

        Question question3 = Question.builder().question("question 3").auditType(AuditType.Internal).response("no").build();
        Question question4 = Question.builder().question("question 4").auditType(AuditType.Internal).response("no").build();
        Question question5 = Question.builder().question("question 5").auditType(AuditType.Internal).response("no").build();

        Question question6 = Question.builder().question("question 6").auditType(AuditType.SOX).response("no").build();
        Question question7 = Question.builder().question("question 7").auditType(AuditType.SOX).response("no").build();
        Question question8 = Question.builder().question("question 8").auditType(AuditType.SOX).response("no").build();

       

        auditDetail1 = AuditDetail.builder().auditDate("25-09-2000").auditQuestions(Arrays.asList(question1,question2)).build();
        auditDetail2 = AuditDetail.builder().auditDate("25-09-2000").auditQuestions(Arrays.asList(question1,question2,question3,question4,question5)).build();
        auditDetail3 = AuditDetail.builder().auditDate("25-09-2000").auditQuestions(Arrays.asList(question6,question7,question8)).build();

        auditRequest1 = AuditRequest.builder()
                                    .projectName("project 1")
                                    .managerName("manager 1")
                                    .auditDetail(auditDetail1).build();

                                    auditRequest2 = AuditRequest.builder()
                                    .projectName("project 2")
                                    .managerName("manager 2")
                                    .auditDetail(auditDetail2).build();

                                    auditRequest3 = AuditRequest.builder()
                                    .projectName("project 3")
                                    .managerName("manager 3")
                                    .auditDetail(auditDetail3).build();

        auditResponseGreen = AuditResponse.builder()
                                        .projectExecutionStatus("GREEN")
                                        .remedialActionDuration("No action needed").build();
        
        auditResponseRedInternal = AuditResponse.builder()
                                        .projectExecutionStatus("RED")
                                        .remedialActionDuration("Action to be taken in 2 weeks").build();

        auditResponseRedSOX = AuditResponse.builder()
                                        .projectExecutionStatus("RED")
                                        .remedialActionDuration("Action to be taken in 1 week").build();

        auditBenchmark1 = AuditBenchmark.builder()
                                        .auditType(AuditType.Internal)
                                        .acceptableNos(3).build();

        auditBenchmark2 = AuditBenchmark.builder()
                                        .auditType(AuditType.SOX)
                                        .acceptableNos(1).build();
    }

    @Test
    public void test_get_auditSeverity_service_correctResponse_green(){

        Assertions.assertThat(auditSeverityService.getAuditSeverityResponse(auditRequest1, auditBenchmark1)).isEqualTo(auditResponseGreen);
    }

    @Test
    public void test_get_auditSeverity_service_correctResponse_red_internal(){

        Assertions.assertThat(auditSeverityService.getAuditSeverityResponse(auditRequest2, auditBenchmark1)).isEqualTo(auditResponseRedInternal);
        
    }

    @Test
    public void test_get_auditSeverity_service_correctResponse_red_SOX(){

        Assertions.assertThat(auditSeverityService.getAuditSeverityResponse(auditRequest3, auditBenchmark2)).isEqualTo(auditResponseRedSOX);
        
    }

}
