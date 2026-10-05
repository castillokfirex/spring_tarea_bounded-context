package com.example.tarea.application.priority.command;

import com.example.tarea.domain.priority.model.valueobject.PriorityId;

public record UpdatePriorityCommand(
        PriorityId id,
        String namePriority
) {
}
