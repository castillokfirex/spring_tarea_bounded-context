package com.example.tarea.infrastructure.chatconversation.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatConversationJpaRepository extends JpaRepository<ChatConversationJpaEntity, UUID> {
}
