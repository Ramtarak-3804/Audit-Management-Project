package com.cognizant.auditchecklist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.auditchecklist.exception.AuthorizationException;
import com.cognizant.auditchecklist.feign.AuthorizationClient;
import com.cognizant.auditchecklist.model.AuditType;
import com.cognizant.auditchecklist.model.Question;
import com.cognizant.auditchecklist.service.AuditQuestionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class AuditChecklistController {

    @Autowired
    AuditQuestionService auditQuestionService;

    @Autowired
    AuthorizationClient authorizationClient;


    @GetMapping("/questions/{auditType}")
    @ApiOperation(notes = "Returns list of question based on audit type requested",value = "getAuditQuestionsByAuditType")
    public List<Question> getAuditQuestionsByAuditType(
        @ApiParam(name = "AuthorizationToken",value = "jwt token from the client")@RequestHeader(value = "Authorization",required = true) String requestTokenHeader,
        @ApiParam(name = "auditType",value = "can either be Internal/SOX")@PathVariable AuditType auditType) throws AuthorizationException{

        if(authorizationClient.authorizeRequest(requestTokenHeader)){
            log.info("preparing to send list of questions for auditType: "+auditType);
            return auditQuestionService.getAuditQuestions(auditType);
        }
        else{
            throw new AuthorizationException("User not allowed to access this resource");
        }
          
    }
}

