package com.cognizant.auditchecklist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.auditchecklist.model.AuditType;
import com.cognizant.auditchecklist.model.Question;
import com.cognizant.auditchecklist.repository.QuestionRepo;

@Service
public class AuditQuestionService {
    
    @Autowired
    QuestionRepo questionRepo;

    public List<Question> getAuditQuestions(AuditType auditType){
        return questionRepo.findByAuditType(auditType);
    }
}
