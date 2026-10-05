package com.example.tarea.application.escalationstatus.command;

import com.example.tarea.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record UpdateEscalationStatusCommand(
        EscalationStatusId id,
        String nameStatus
) {
}
