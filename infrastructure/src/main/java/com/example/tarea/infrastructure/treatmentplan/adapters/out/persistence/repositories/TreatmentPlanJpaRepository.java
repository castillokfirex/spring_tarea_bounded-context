package com.example.tarea.infrastructure.treatmentplan.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.treatmentplan.adapters.out.persistence.entity.TreatmentPlanJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentPlanJpaRepository extends JpaRepository<TreatmentPlanJpaEntity, UUID> {
}
