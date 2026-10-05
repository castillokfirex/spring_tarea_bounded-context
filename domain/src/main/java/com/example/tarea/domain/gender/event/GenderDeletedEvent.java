package com.example.tarea.domain.gender.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.gender.model.valueobject.GenderId;

public record GenderDeletedEvent(
        GenderId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
