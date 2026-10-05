package com.example.tarea.application.airunstatus.command;

import com.example.tarea.domain.airunstatus.model.valueobject.AiRunStatusId;

public record UpdateAiRunStatusCommand(
        AiRunStatusId id,
        String nameStatus
) {
}
