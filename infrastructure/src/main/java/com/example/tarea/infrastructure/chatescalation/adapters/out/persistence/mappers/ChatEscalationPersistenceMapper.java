package com.example.tarea.infrastructure.chatescalation.adapters.out.persistence.mappers;

import com.example.tarea.domain.chatescalation.model.aggregate.ChatEscalation;
import com.example.tarea.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.example.tarea.infrastructure.chatescalation.adapters.out.persistence.entity.ChatEscalationJpaEntity;

public class ChatEscalationPersistenceMapper {

    public ChatEscalationJpaEntity toJpa(ChatEscalation domain) {

        if (domain == null) {
            return null;
        }

        ChatEscalationJpaEntity jpa = new ChatEscalationJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId());
        jpa.setStatusId(domain.statusId());
        jpa.setFromAi(domain.fromAi());
        jpa.setReason(domain.reason());
        jpa.setCreatedAt(domain.createdAt());

        return jpa;
    }

    public ChatEscalation toDomain(ChatEscalationJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return ChatEscalation.restore(
                new ChatEscalationId(jpa.getId()),
                jpa.getConversationId(),
                jpa.getStatusId(),
                jpa.getFromAi(),
                jpa.getReason(),
                jpa.getCreatedAt());
    }
}
