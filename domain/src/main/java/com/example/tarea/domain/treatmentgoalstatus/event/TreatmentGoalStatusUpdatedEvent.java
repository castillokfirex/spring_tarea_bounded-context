package com.example.tarea.domain.treatmentgoalstatus.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public record TreatmentGoalStatusUpdatedEvent(
        TreatmentGoalStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
