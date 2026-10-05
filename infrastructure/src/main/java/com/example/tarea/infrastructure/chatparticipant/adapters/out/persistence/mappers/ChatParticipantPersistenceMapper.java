package com.example.tarea.infrastructure.chatparticipant.adapters.out.persistence.mappers;

import com.example.tarea.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.example.tarea.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.example.tarea.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;

public class ChatParticipantPersistenceMapper {

    public ChatParticipantJpaEntity toJpa(ChatParticipant domain) {

        if (domain == null) {
            return null;
        }

        ChatParticipantJpaEntity jpa = new ChatParticipantJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId());
        jpa.setParticipantTypeId(domain.participantTypeId());
        jpa.setPatientId(domain.patientId());
        jpa.setProfessionalId(domain.professionalId());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());

        return jpa;
    }

    public ChatParticipant toDomain(ChatParticipantJpaEntity jpa) {

        if (jpa == null) {
            return null;
        }

        return ChatParticipant.restore(
                new ChatParticipantId(jpa.getId()),
                jpa.getConversationId(),
                jpa.getParticipantTypeId(),
                jpa.getPatientId(),
                jpa.getProfessionalId(),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
