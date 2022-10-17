package com.cognizant.auditseverity.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.cognizant.auditseverity.model.AuditBenchmark;
import com.cognizant.auditseverity.model.AuditRequest;
import com.cognizant.auditseverity.model.AuditResponse;
import com.cognizant.auditseverity.model.AuditType;
import com.cognizant.auditseverity.model.Question;

@Service
public class AuditSeverityService {
    

    public AuditResponse getAuditSeverityResponse(AuditRequest auditRequest,AuditBenchmark auditBenchmark){
        int countOfNos = 0;
        List<Question> auditQuestions = auditRequest.getAuditDetail().getAuditQuestions();
        for(int i = 0;i<auditQuestions.size();i++){
            if(auditQuestions.get(i).getResponse().equalsIgnoreCase("no")){
                countOfNos++;
            }
        }
        if(countOfNos<=auditBenchmark.getAcceptableNos()){
            return new AuditResponse(auditRequest.getAuditRequestId(),"GREEN","No action needed");
        }
        else{
            if(auditBenchmark.getAuditType()==AuditType.Internal)
                return new AuditResponse(auditRequest.getAuditRequestId(),"RED","Action to be taken in 2 weeks");
            else
                return new AuditResponse(auditRequest.getAuditRequestId(),"RED","Action to be taken in 1 week");
        }
    }

}
