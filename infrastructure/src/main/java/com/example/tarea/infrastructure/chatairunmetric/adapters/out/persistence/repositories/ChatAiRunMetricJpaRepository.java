package com.example.tarea.infrastructure.chatairunmetric.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.chatairunmetric.adapters.out.persistence.entity.ChatAiRunMetricJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatAiRunMetricJpaRepository extends JpaRepository<ChatAiRunMetricJpaEntity, UUID> {
}
