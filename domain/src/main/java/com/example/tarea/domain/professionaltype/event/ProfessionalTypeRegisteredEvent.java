package com.example.tarea.domain.professionaltype.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public record ProfessionalTypeRegisteredEvent(
        ProfessionalTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
