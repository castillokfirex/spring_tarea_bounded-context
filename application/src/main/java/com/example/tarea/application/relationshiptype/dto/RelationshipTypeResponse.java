package com.example.tarea.application.relationshiptype.dto;

import java.util.UUID;

import com.example.tarea.domain.relationshiptype.model.aggregate.RelationshipType;

public record RelationshipTypeResponse(
        UUID id,
        String description) {

    public static RelationshipTypeResponse fromDomain(RelationshipType aggregate) {
        return new RelationshipTypeResponse(
                aggregate.id().value(),
                aggregate.description());
    }
}
