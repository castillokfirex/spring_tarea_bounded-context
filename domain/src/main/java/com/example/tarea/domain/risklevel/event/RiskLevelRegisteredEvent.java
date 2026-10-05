package com.example.tarea.domain.risklevel.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.risklevel.model.valueobject.RiskLevelId;

public record RiskLevelRegisteredEvent(
        RiskLevelId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
