package com.cognizant.auditseverity.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "auditDetail")
public class AuditDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int detailId;
    private String auditDate;
    @OneToMany(targetEntity=Question.class, mappedBy="auditDetail", fetch=FetchType.EAGER)
    private List<Question> auditQuestions;

}
