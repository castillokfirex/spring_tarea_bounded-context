package com.example.tarea.domain.relationshiptype.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado RelationshipType (value object).
 */
public record RelationshipTypeId(UUID value) {

    public RelationshipTypeId {
        if (value == null) {
            throw new DomainValidationException("RelationshipTypeId value must not be null");
        }
    }

    public static RelationshipTypeId generate() {
        return new RelationshipTypeId(UUID.randomUUID());
    }

    public static RelationshipTypeId of(UUID value) {
        return new RelationshipTypeId(value);
    }
}
