package com.example.tarea.domain.assessmenttype.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.assessmenttype.model.valueobject.AssessmentTypeId;

public record AssessmentTypeUpdatedEvent(
        AssessmentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
