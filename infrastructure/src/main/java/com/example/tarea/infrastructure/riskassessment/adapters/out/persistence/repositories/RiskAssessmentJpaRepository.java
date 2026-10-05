package com.example.tarea.infrastructure.riskassessment.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiskAssessmentJpaRepository extends JpaRepository<RiskAssessmentJpaEntity, UUID> {
}
