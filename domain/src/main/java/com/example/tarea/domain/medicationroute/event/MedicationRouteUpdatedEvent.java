package com.example.tarea.domain.medicationroute.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.medicationroute.model.valueobject.MedicationRouteId;

public record MedicationRouteUpdatedEvent(
        MedicationRouteId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
