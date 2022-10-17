package com.cognizant.auditbenchmark.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.auditbenchmark.model.AuditBenchmark;
import com.cognizant.auditbenchmark.repository.AuditBenchmarkRepo;

@Service
public class AuditBenchmarkService {
    @Autowired
    AuditBenchmarkRepo auditBenchmarkRepo;

    public List<AuditBenchmark> getAuditBenchmark(){
        return auditBenchmarkRepo.findAll();
    }
}
