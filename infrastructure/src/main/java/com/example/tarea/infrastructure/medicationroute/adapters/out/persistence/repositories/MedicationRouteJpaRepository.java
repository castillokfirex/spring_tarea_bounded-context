package com.example.tarea.infrastructure.medicationroute.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.medicationroute.adapters.out.persistence.entity.MedicationRouteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRouteJpaRepository extends JpaRepository<MedicationRouteJpaEntity, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);
}
