package com.cognizant.auditchecklist.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.auditchecklist.model.AuditType;
import com.cognizant.auditchecklist.model.Question;
import com.cognizant.auditchecklist.repository.QuestionRepo;

@ExtendWith(MockitoExtension.class)
public class AuditChecklistServiceTest {
    
    @Mock
    QuestionRepo questionRepo;

    @InjectMocks
    AuditQuestionService auditQuestionService;

    private Question question1;
    private Question question2;


    @BeforeEach
	public void setup() {
		
		question1 = new Question(101, "question1", AuditType.Internal, "");
        question2 = new Question(102, "question2", AuditType.Internal, "");  
        
	}

    @Test
    public void testGetAuditQuestions(){
        when(questionRepo.findByAuditType(AuditType.Internal)).thenReturn(Arrays.asList(question1,question2));

        assertThat(auditQuestionService.getAuditQuestions(AuditType.Internal)).hasSize(2); 
    }
}
