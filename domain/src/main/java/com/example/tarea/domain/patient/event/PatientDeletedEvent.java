package com.example.tarea.domain.patient.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.patient.model.valueobject.PatientId;

public record PatientDeletedEvent(
        PatientId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
