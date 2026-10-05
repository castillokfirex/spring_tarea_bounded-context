package com.example.tarea.infrastructure.chatairunerror.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatAiRunErrorJpaRepository extends JpaRepository<ChatAiRunErrorJpaEntity, UUID> {
}
