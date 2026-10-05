package com.example.tarea.infrastructure.priority.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.priority.adapters.out.persistence.entity.PriorityJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityJpaRepository extends JpaRepository<PriorityJpaEntity, UUID> {
}
