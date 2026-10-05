package com.example.tarea.infrastructure.chatairun.adapters.out.persistence.mappers;

import com.example.tarea.domain.chatairun.model.aggregate.ChatAiRun;
import com.example.tarea.domain.chatairun.model.valueobject.ChatAiRunId;
import com.example.tarea.infrastructure.chatairun.adapters.out.persistence.entity.ChatAiRunJpaEntity;

public class ChatAiRunPersistenceMapper {

    public ChatAiRunJpaEntity toJpa(ChatAiRun domain) {

        if (domain == null) {
            return null;
        }

        ChatAiRunJpaEntity jpa = new ChatAiRunJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId());
        jpa.setMessageId(domain.messageId());
        jpa.setModelId(domain.modelId());
        jpa.setAiRunStatusId(domain.aiRunStatusId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ChatAiRun toDomain(ChatAiRunJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return ChatAiRun.restore(
                new ChatAiRunId(jpa.getId()),
                jpa.getConversationId(),
                jpa.getMessageId(),
                jpa.getModelId(),
                jpa.getAiRunStatusId(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
