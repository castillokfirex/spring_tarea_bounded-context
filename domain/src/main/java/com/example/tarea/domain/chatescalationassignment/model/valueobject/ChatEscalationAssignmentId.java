package com.example.tarea.domain.chatescalationassignment.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado ChatEscalationAssignment (value object).
 */
public record ChatEscalationAssignmentId(UUID value) {

    public ChatEscalationAssignmentId {
        if (value == null) {
            throw new DomainValidationException("ChatEscalationAssignmentId value must not be null");
        }
    }

    public static ChatEscalationAssignmentId generate() {
        return new ChatEscalationAssignmentId(UUID.randomUUID());
    }

    public static ChatEscalationAssignmentId of(UUID value) {
        return new ChatEscalationAssignmentId(value);
    }
}
