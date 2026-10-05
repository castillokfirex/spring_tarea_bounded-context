package com.example.tarea.infrastructure.encounter.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.encounter.adapters.out.persistence.entity.EncounterJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncounterJpaRepository extends JpaRepository<EncounterJpaEntity, UUID> {
}
