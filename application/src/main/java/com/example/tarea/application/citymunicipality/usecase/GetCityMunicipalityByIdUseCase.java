package com.example.tarea.application.citymunicipality.usecase;

import com.example.tarea.application.citymunicipality.dto.CityMunicipalityResponse;
import com.example.tarea.domain.citymunicipality.exception.CityMunicipalityNotFoundException;
import com.example.tarea.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.example.tarea.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class GetCityMunicipalityByIdUseCase {

    private final CityMunicipalityRepository repository;

    public GetCityMunicipalityByIdUseCase(CityMunicipalityRepository repository) {
        this.repository = repository;
    }

    public CityMunicipalityResponse execute(CityMunicipalityId id) {
        return repository.findById(id)
                .map(CityMunicipalityResponse::fromDomain)
                .orElseThrow(() -> new CityMunicipalityNotFoundException(id));
    }
}
