package com.example.tarea.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateChatEscalationStatusHistoryRequest(
        @NotNull UUID escalationId,
        @NotNull UUID escalationStatusId,
        @NotNull LocalDateTime changedAt
) {
}
