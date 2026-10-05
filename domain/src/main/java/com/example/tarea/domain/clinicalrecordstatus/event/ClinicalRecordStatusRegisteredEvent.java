package com.example.tarea.domain.clinicalrecordstatus.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public record ClinicalRecordStatusRegisteredEvent(
        ClinicalRecordStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
