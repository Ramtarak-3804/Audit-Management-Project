package com.cognizant.auditseverity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "benchmark")
public class AuditBenchmark {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int benchmarkId;
    private AuditType auditType;
    private int acceptableNos;
}
