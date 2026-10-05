package com.example.tarea.domain.chatescalationassignment.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

public record ChatEscalationAssignmentRegisteredEvent(
        ChatEscalationAssignmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
