package com.example.tarea.infrastructure.chatairun.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.chatairun.adapters.out.persistence.entity.ChatAiRunJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatAiRunJpaRepository extends JpaRepository<ChatAiRunJpaEntity, UUID> {
}
