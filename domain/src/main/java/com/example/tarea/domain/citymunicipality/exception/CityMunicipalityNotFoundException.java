package com.example.tarea.domain.citymunicipality.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.citymunicipality.model.valueobject.CityMunicipalityId;

public class CityMunicipalityNotFoundException extends ResourceNotFoundException {

    public CityMunicipalityNotFoundException(CityMunicipalityId id) {
        super("CityMunicipality with id '" + id.value() + "' was not found");
    }
}
