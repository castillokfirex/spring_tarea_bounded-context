package com.example.tarea.domain.country.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado Country (value object).
 */
public record CountryId(UUID value) {

    public CountryId {
        if (value == null) {
            throw new DomainValidationException("CountryId value must not be null");
        }
    }

    public static CountryId generate() {
        return new CountryId(UUID.randomUUID());
    }

    public static CountryId of(UUID value) {
        return new CountryId(value);
    }
}
