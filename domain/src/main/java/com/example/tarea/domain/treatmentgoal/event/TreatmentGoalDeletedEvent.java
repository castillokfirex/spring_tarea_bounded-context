package com.example.tarea.domain.treatmentgoal.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

public record TreatmentGoalDeletedEvent(
        TreatmentGoalId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
