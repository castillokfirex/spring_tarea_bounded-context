package com.example.tarea.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatConversationAiSettingJpaRepository extends JpaRepository<ChatConversationAiSettingJpaEntity, UUID> {
}
