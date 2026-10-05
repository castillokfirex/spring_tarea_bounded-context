package com.example.tarea.infrastructure.chatconversation.adapters.out.persistence.mappers;

import com.example.tarea.domain.chatconversation.model.aggregate.ChatConversation;
import com.example.tarea.domain.chatconversation.model.valueobject.ChatConversationId;
import com.example.tarea.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;

public class ChatConversationPersistenceMapper {

    public ChatConversationJpaEntity toJpa(ChatConversation domain) {

        if (domain == null) {
            return null;
        }

        ChatConversationJpaEntity jpa = new ChatConversationJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationStatusId(domain.conversationStatusId());
        jpa.setPriorityId(domain.priorityId());
        jpa.setLastMessageAt(domain.lastMessageAt());
        jpa.setClosed(domain.closed());
        jpa.setClosedAt(domain.closedAt());
        jpa.setClosedBy(domain.closedBy());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ChatConversation toDomain(ChatConversationJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return ChatConversation.restore(
                new ChatConversationId(jpa.getId()),
                jpa.getConversationStatusId(),
                jpa.getPriorityId(),
                jpa.getLastMessageAt(),
                jpa.getClosed(),
                jpa.getClosedAt(),
                jpa.getClosedBy(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
