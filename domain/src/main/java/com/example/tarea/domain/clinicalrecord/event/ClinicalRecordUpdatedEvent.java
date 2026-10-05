package com.example.tarea.domain.clinicalrecord.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

public record ClinicalRecordUpdatedEvent(
        ClinicalRecordId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
