package com.example.tarea.infrastructure.encountermodality.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.encountermodality.adapters.out.persistence.entity.EncounterModalityJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncounterModalityJpaRepository extends JpaRepository<EncounterModalityJpaEntity, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);
}
