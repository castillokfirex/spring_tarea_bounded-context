package com.example.tarea.infrastructure.professionaltype.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalTypeJpaRepository extends JpaRepository<ProfessionalTypeJpaEntity, UUID> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);
}
