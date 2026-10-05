package com.example.tarea.infrastructure.chatescalationassignment.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateChatEscalationAssignmentRequest(
        @NotNull UUID escalationId,
        @NotNull UUID professionalId,
        LocalDateTime assignedAt
) {
}
