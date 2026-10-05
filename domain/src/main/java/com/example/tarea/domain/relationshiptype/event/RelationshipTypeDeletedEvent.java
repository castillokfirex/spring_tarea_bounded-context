package com.example.tarea.domain.relationshiptype.event;

import java.time.LocalDateTime;

import com.example.tarea.domain.common.event.DomainEvent;
import com.example.tarea.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public record RelationshipTypeDeletedEvent(
        RelationshipTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
}
