package com.example.tarea.domain.encounterstatus.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.encounterstatus.model.valueobject.EncounterStatusId;

public record EncounterStatusRegisteredEvent(
        EncounterStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
