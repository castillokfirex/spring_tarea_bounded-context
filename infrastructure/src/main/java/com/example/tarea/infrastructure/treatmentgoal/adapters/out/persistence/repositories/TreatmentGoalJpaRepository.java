package com.example.tarea.infrastructure.treatmentgoal.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.treatmentgoal.adapters.out.persistence.entity.TreatmentGoalJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentGoalJpaRepository extends JpaRepository<TreatmentGoalJpaEntity, UUID> {
}
