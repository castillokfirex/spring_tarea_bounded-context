package com.example.tarea.domain.documenttype.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado DocumentType (value object).
 */
public record DocumentTypeId(UUID value) {

    public DocumentTypeId {
        if (value == null) {
            throw new DomainValidationException("DocumentTypeId value must not be null");
        }
    }

    public static DocumentTypeId generate() {
        return new DocumentTypeId(UUID.randomUUID());
    }

    public static DocumentTypeId of(UUID value) {
        return new DocumentTypeId(value);
    }
}
