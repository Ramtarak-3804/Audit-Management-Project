package com.cognizant.auditseverity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.auditseverity.exception.AuthorizationException;
import com.cognizant.auditseverity.feign.AuthorizationClient;
import com.cognizant.auditseverity.feign.BenchmarkClient;
import com.cognizant.auditseverity.model.AuditRequest;
import com.cognizant.auditseverity.model.AuditResponse;
import com.cognizant.auditseverity.model.AuditType;
import com.cognizant.auditseverity.service.AuditSeverityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@CrossOrigin
public class AuditSeverityController {
    
    @Autowired
    AuditSeverityService auditSeverityService;

    @Autowired
    AuthorizationClient authorizationClient;

    @Autowired
    BenchmarkClient benchmarkClient;

    @PostMapping("/severity")
    @ApiOperation(notes = "returns severity of audit response by benchmarking it",value = "getAuditSeverity")
    public AuditResponse getAuditSeverity(
        @ApiParam(name = "AuthorizationToken",value = "jwt token from the client")@RequestHeader(value = "Authorization",required = true)String requestTokenHeader,
        @ApiParam(name = "auditRequest",value = "contains details of audit response from client")@RequestBody AuditRequest auditRequest
        )throws AuthorizationException{
            if(authorizationClient.authorizeRequest(requestTokenHeader)){
                AuditType auditType = auditRequest.getAuditDetail().getAuditQuestions().get(0).getAuditType();
                int index = 1;
                if(auditType==AuditType.Internal){
                    index = 0;
                }
                return auditSeverityService.getAuditSeverityResponse(auditRequest, benchmarkClient.getAuditBenchmark(requestTokenHeader).get(index));
            }
            else{
                throw new AuthorizationException("User not allowed to access this resource");
            }
        }

}
