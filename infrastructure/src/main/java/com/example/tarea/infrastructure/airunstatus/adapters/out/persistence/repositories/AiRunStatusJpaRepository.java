package com.example.tarea.infrastructure.airunstatus.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.airunstatus.adapters.out.persistence.entity.AiRunStatusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiRunStatusJpaRepository extends JpaRepository<AiRunStatusJpaEntity, UUID> {
}
