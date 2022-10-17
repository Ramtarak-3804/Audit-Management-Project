package com.cognizant.auditchecklist.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="question")
public class Question {

    @Id
    private int questionId;
    private String question;
    private AuditType auditType;
    private String response;
}
