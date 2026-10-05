package com.example.tarea.infrastructure.aimodel.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.aimodel.adapters.out.persistence.entity.AiModelJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiModelJpaRepository extends JpaRepository<AiModelJpaEntity, UUID> {
}
