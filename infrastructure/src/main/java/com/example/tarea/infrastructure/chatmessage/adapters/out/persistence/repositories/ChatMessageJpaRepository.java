package com.example.tarea.infrastructure.chatmessage.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.chatmessage.adapters.out.persistence.entity.ChatMessageJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageJpaRepository extends JpaRepository<ChatMessageJpaEntity, UUID> {
}
