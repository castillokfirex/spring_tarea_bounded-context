package com.example.tarea.domain.country.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.country.model.valueobject.CountryId;

public record CountryUpdatedEvent(
        CountryId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
