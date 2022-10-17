package com.cognizant.auditbenchmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.auditbenchmark.model.AuditBenchmark;

@Repository
public interface AuditBenchmarkRepo extends JpaRepository<AuditBenchmark,Integer> {
    
}
