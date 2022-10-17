package com.cognizant.auditseverity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name="question")
public class Question {

    @Id@GeneratedValue(strategy= GenerationType.AUTO)
    private int questionId;
    private String question;
    private AuditType auditType;
    private String response;
    @ManyToOne
    @JoinColumn(name="auditDetail_id", nullable=false)
    private AuditDetail auditDetail;
}
