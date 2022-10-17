package com.cognizant.auditseverity.feign;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.auditseverity.model.AuditBenchmark;



@FeignClient(name = "Audit-Benchmark",url = "${benchmark.URL}")
public interface BenchmarkClient {
    
    @GetMapping("/benchmark")
    public List<AuditBenchmark> getAuditBenchmark(@RequestHeader(name = "Authorization",required = true)String requestTokenHeader);
    
}
