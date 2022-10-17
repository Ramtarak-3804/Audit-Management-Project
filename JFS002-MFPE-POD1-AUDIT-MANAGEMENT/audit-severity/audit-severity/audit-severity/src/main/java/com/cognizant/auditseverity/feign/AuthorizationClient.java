package com.cognizant.auditseverity.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Authorization-Microservice",url = "${auth.URL}")
public interface AuthorizationClient {
    
    @PostMapping("/authorize")
    public boolean authorizeRequest(@RequestHeader(value = "Authorization",required = true)String requestTokenHeader);
}
