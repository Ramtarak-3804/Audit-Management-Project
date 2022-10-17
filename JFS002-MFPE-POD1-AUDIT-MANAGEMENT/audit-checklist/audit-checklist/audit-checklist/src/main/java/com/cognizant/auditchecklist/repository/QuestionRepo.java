package com.cognizant.auditchecklist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.auditchecklist.model.AuditType;
import com.cognizant.auditchecklist.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findByAuditType(AuditType auditType);
}
