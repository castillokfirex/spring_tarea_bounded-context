package com.example.tarea.infrastructure.chatescalationassignment.adapters.out.persistence.mappers;

import com.example.tarea.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.example.tarea.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.example.tarea.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;

public class ChatEscalationAssignmentPersistenceMapper {

    public ChatEscalationAssignmentJpaEntity toJpa(ChatEscalationAssignment domain) {

        if (domain == null) {
            return null;
        }

        ChatEscalationAssignmentJpaEntity jpa = new ChatEscalationAssignmentJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setEscalationId(domain.escalationId());
        jpa.setProfessionalId(domain.professionalId());
        jpa.setAssignedAt(domain.assignedAt());

        return jpa;
    }

    public ChatEscalationAssignment toDomain(ChatEscalationAssignmentJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return ChatEscalationAssignment.restore(
                new ChatEscalationAssignmentId(jpa.getId()),
                jpa.getEscalationId(),
                jpa.getProfessionalId(),
                jpa.getAssignedAt());
    }
}
