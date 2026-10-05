package com.example.tarea.domain.professionalstudy.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public record ProfessionalStudyUpdatedEvent(
        ProfessionalStudyId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
