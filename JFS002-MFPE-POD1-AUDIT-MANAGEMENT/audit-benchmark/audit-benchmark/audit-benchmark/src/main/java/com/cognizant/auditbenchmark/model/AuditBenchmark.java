package com.cognizant.auditbenchmark.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "benchmark")
public class AuditBenchmark {

    @Id
    private int benchmarkId;
    private AuditType auditType;
    private int acceptableNos;
}
