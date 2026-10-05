package com.example.tarea.infrastructure.chatescalationstatushistory.adapters.out.persistence.mappers;

import com.example.tarea.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.example.tarea.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.example.tarea.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity.ChatEscalationStatusHistoryJpaEntity;

public class ChatEscalationStatusHistoryPersistenceMapper {

    public ChatEscalationStatusHistoryJpaEntity toJpa(ChatEscalationStatusHistory domain) {

        if (domain == null) {
            return null;
        }

        ChatEscalationStatusHistoryJpaEntity jpa = new ChatEscalationStatusHistoryJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setEscalationId(domain.escalationId());
        jpa.setEscalationStatusId(domain.escalationStatusId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setChangedAt(domain.changedAt());

        return jpa;
    }

    public ChatEscalationStatusHistory toDomain(ChatEscalationStatusHistoryJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return ChatEscalationStatusHistory.restore(
                new ChatEscalationStatusHistoryId(jpa.getId()),
                jpa.getEscalationId(),
                jpa.getEscalationStatusId(),
                jpa.getCreatedAt(),
                jpa.getChangedAt());
    }
}
