package com.example.tarea.infrastructure.gender.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.gender.adapters.out.persistence.entity.GenderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderJpaRepository extends JpaRepository<GenderJpaEntity, UUID> {

    boolean existsByDescription(String description);

    boolean existsByDescriptionAndIdNot(String description, UUID id);
}
