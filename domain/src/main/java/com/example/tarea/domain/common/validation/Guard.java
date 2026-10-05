package com.example.tarea.domain.common.validation;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Validaciones reutilizables para proteger las invariantes de los agregados.
 */
public final class Guard {

    private Guard() {
    }

    public static <T> T notNull(T value, String field) {
        if (value == null) {
            throw new DomainValidationException(field + " must not be null");
        }
        return value;
    }

    public static String notBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new DomainValidationException(field + " must not be blank");
        }
        return value;
    }

    public static String maxLength(String value, int max, String field) {
        if (value != null && value.length() > max) {
            throw new DomainValidationException(field + " must have at most " + max + " characters");
        }
        return value;
    }
}
