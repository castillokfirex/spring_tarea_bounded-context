package com.example.tarea.infrastructure.relationshiptype.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.relationshiptype.adapters.out.persistence.entity.RelationshipTypeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipTypeJpaRepository extends JpaRepository<RelationshipTypeJpaEntity, UUID> {

    boolean existsByDescription(String description);

    boolean existsByDescriptionAndIdNot(String description, UUID id);
}
