package com.cognizant.auditseverity.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "auditRequest")
public class AuditRequest {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int auditRequestId;
    private String projectName;
    private String managerName;
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="auditDetail", unique=true)
    private AuditDetail auditDetail;
}
