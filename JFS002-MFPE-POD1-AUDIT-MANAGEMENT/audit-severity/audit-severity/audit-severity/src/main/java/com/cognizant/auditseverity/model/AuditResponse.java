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
@Table(name = "auditResponse")
public class AuditResponse {
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int auditResponseId;
    private String projectExecutionStatus;
    private String remedialActionDuration;
}
