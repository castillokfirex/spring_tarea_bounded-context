package com.example.tarea.infrastructure.assessmenttype.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.assessmenttype.adapters.out.persistence.entity.AssessmentTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentTypeJpaRepository extends JpaRepository<AssessmentTypeJpaEntity, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);
}
