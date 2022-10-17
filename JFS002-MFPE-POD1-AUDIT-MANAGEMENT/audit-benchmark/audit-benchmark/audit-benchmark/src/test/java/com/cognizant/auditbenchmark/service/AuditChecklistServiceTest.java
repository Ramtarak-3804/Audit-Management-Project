package com.cognizant.auditbenchmark.service;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.auditbenchmark.model.AuditBenchmark;
import com.cognizant.auditbenchmark.model.AuditType;
import com.cognizant.auditbenchmark.repository.AuditBenchmarkRepo;

@ExtendWith(MockitoExtension.class)
public class AuditChecklistServiceTest {
    
    @Mock
    AuditBenchmarkRepo auditBenchmarkRepo;

    @InjectMocks
    AuditBenchmarkService auditBenchmarkService;

    private AuditBenchmark auditBenchmark1;
    private AuditBenchmark auditBenchmark2;

    @BeforeEach
    void setup(){
        auditBenchmark1 = new AuditBenchmark(1, AuditType.Internal, 3);
        auditBenchmark1 = new AuditBenchmark(2, AuditType.SOX, 1);

    }

    @Test
    void testGetAuditBenchmarkService(){
        when(auditBenchmarkRepo.findAll()).thenReturn(Arrays.asList(auditBenchmark1,auditBenchmark2));
        Assertions.assertThat(auditBenchmarkService.getAuditBenchmark()).isEqualTo(Arrays.asList(auditBenchmark1,auditBenchmark2));
    }
}
