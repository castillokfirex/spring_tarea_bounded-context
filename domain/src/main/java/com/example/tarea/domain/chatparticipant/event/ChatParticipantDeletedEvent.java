package com.example.tarea.domain.chatparticipant.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.chatparticipant.model.valueobject.ChatParticipantId;

public record ChatParticipantDeletedEvent(
        ChatParticipantId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
