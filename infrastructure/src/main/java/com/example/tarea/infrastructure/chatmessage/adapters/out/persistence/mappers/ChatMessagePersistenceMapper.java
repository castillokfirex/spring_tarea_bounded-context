package com.example.tarea.infrastructure.chatmessage.adapters.out.persistence.mappers;

import com.example.tarea.domain.chatmessage.model.aggregate.ChatMessage;
import com.example.tarea.domain.chatmessage.model.valueobject.ChatMessageId;
import com.example.tarea.infrastructure.chatmessage.adapters.out.persistence.entity.ChatMessageJpaEntity;

public class ChatMessagePersistenceMapper {

    public ChatMessageJpaEntity toJpa(ChatMessage domain) {

        if (domain == null) {
            return null;
        }

        ChatMessageJpaEntity jpa = new ChatMessageJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId());
        jpa.setMessageTypeId(domain.messageTypeId());
        jpa.setParticipantId(domain.participantId());
        jpa.setContent(domain.content());
        jpa.setMetadata(domain.metadata());
        jpa.setCreatedAt(domain.createdAt());

        return jpa;
    }

    public ChatMessage toDomain(ChatMessageJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return ChatMessage.restore(
                new ChatMessageId(jpa.getId()),
                jpa.getConversationId(),
                jpa.getMessageTypeId(),
                jpa.getParticipantId(),
                jpa.getContent(),
                jpa.getMetadata(),
                jpa.getCreatedAt());
    }
}
