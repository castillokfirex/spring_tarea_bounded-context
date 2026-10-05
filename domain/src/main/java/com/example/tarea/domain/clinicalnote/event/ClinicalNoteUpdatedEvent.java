package com.example.tarea.domain.clinicalnote.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.clinicalnote.model.valueobject.ClinicalNoteId;

public record ClinicalNoteUpdatedEvent(
        ClinicalNoteId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
