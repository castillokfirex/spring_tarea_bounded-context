package com.example.tarea.infrastructure.treatmentgoalstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity.TreatmentGoalStatusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentGoalStatusJpaRepository extends JpaRepository<TreatmentGoalStatusJpaEntity, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);
}
