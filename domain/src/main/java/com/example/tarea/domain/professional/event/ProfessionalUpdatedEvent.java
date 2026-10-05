package com.example.tarea.domain.professional.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.professional.model.valueobject.ProfessionalId;

public record ProfessionalUpdatedEvent(
        ProfessionalId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
