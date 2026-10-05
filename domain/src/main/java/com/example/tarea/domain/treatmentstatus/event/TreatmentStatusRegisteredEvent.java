package com.example.tarea.domain.treatmentstatus.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public record TreatmentStatusRegisteredEvent(
        TreatmentStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
