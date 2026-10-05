package com.example.tarea.domain.citymunicipality.model.valueobject;

import java.util.UUID;

import com.example.tarea.domain.common.exception.DomainValidationException;

/**
 * Identificador del agregado CityMunicipality (value object).
 */
public record CityMunicipalityId(UUID value) {

    public CityMunicipalityId {
        if (value == null) {
            throw new DomainValidationException("CityMunicipalityId value must not be null");
        }
    }

    public static CityMunicipalityId generate() {
        return new CityMunicipalityId(UUID.randomUUID());
    }

    public static CityMunicipalityId of(UUID value) {
        return new CityMunicipalityId(value);
    }
}
