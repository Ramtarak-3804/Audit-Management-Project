package com.cognizant.auditbenchmark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.auditbenchmark.exception.AuthorizationException;
import com.cognizant.auditbenchmark.feign.AuthorizationClient;
import com.cognizant.auditbenchmark.model.AuditBenchmark;
import com.cognizant.auditbenchmark.service.AuditBenchmarkService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuditBenchmarkController {
    
    @Autowired
    AuditBenchmarkService auditBenchmarkService;

    @Autowired
    AuthorizationClient authorizationClient;

    @GetMapping("/benchmark")
    @ApiOperation(notes = "Returns the benchmarks for audit responses",value = "getAuditBenchmark")
    public List<AuditBenchmark> getAuditBenchmark(
        @ApiParam(name = "AuthorizationToken",value = "jwt token from the client")@RequestHeader(value = "Authorization",required = true) String requestTokenHeader) throws AuthorizationException{
        if(authorizationClient.authorizeRequest(requestTokenHeader)){
            log.info("preparing for sending benchmarks");
            return auditBenchmarkService.getAuditBenchmark();
        }
        else{
            throw new AuthorizationException("User not allowed to access this resource");
        }
    }
}
