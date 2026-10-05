package com.example.tarea.domain.riskassessment.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.riskassessment.model.valueobject.RiskAssessmentId;

public record RiskAssessmentRegisteredEvent(
        RiskAssessmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
