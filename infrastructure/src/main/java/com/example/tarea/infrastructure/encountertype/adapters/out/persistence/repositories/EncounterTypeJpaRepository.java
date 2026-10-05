package com.example.tarea.infrastructure.encountertype.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncounterTypeJpaRepository extends JpaRepository<EncounterTypeJpaEntity, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);
}
