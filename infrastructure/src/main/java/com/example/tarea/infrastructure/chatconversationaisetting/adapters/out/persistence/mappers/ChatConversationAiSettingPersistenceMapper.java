package com.example.tarea.infrastructure.chatconversationaisetting.adapters.out.persistence.mappers;

import com.example.tarea.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.example.tarea.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.example.tarea.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;

public class ChatConversationAiSettingPersistenceMapper {

    public ChatConversationAiSettingJpaEntity toJpa(ChatConversationAiSetting domain) {

        if (domain == null) {
            return null;
        }

        ChatConversationAiSettingJpaEntity jpa = new ChatConversationAiSettingJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId());
        jpa.setAiEnabled(domain.aiEnabled());
        jpa.setDefaultModelId(domain.defaultModelId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ChatConversationAiSetting toDomain(ChatConversationAiSettingJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return ChatConversationAiSetting.restore(
                new ChatConversationAiSettingId(jpa.getId()),
                jpa.getConversationId(),
                jpa.getAiEnabled(),
                jpa.getDefaultModelId(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
