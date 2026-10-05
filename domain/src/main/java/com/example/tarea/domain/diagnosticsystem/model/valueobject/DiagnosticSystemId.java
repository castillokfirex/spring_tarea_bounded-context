package com.example.tarea.domain.diagnosticsystem.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado DiagnosticSystem (value object).
 */
public record DiagnosticSystemId(UUID value) {

    public DiagnosticSystemId {
        if (value == null) {
            throw new DomainValidationException("DiagnosticSystemId value must not be null");
        }
    }

    public static DiagnosticSystemId generate() {
        return new DiagnosticSystemId(UUID.randomUUID());
    }

    public static DiagnosticSystemId of(UUID value) {
        return new DiagnosticSystemId(value);
    }
}
