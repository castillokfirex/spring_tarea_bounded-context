package com.example.tarea.infrastructure.encounterstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.encounterstatus.adapters.out.persistence.entity.EncounterStatusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncounterStatusJpaRepository extends JpaRepository<EncounterStatusJpaEntity, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);
}
