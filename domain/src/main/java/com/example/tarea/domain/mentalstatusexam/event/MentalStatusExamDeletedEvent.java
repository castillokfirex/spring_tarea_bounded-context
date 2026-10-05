package com.example.tarea.domain.mentalstatusexam.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

public record MentalStatusExamDeletedEvent(
        MentalStatusExamId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
