package com.example.tarea.application.relationshiptype.command;

import com.example.tarea.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public record UpdateRelationshipTypeCommand(
        RelationshipTypeId id,
        String description
) {
}
