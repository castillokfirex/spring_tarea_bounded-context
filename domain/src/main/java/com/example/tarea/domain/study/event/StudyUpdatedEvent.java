package com.example.tarea.domain.study.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.study.model.valueobject.StudyId;

public record StudyUpdatedEvent(
        StudyId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
