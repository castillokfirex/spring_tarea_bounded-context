package com.example.tarea.domain.treatmentplan.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public record TreatmentPlanRegisteredEvent(
        TreatmentPlanId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
