package com.example.tarea.infrastructure.treatmentstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.treatmentstatus.adapters.out.persistence.entity.TreatmentStatusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentStatusJpaRepository extends JpaRepository<TreatmentStatusJpaEntity, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);
}
