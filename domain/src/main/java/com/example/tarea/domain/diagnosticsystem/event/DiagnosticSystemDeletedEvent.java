package com.example.tarea.domain.diagnosticsystem.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

public record DiagnosticSystemDeletedEvent(
        DiagnosticSystemId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
