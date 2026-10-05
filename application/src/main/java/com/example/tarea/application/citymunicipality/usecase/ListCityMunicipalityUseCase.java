package com.example.tarea.application.citymunicipality.usecase;

import java.util.List;

import com.example.tarea.application.citymunicipality.dto.CityMunicipalityResponse;
import com.example.tarea.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class ListCityMunicipalityUseCase {

    private final CityMunicipalityRepository repository;

    public ListCityMunicipalityUseCase(CityMunicipalityRepository repository) {
        this.repository = repository;
    }

    public List<CityMunicipalityResponse> execute() {
        return repository.findAll()
                .stream()
                .map(CityMunicipalityResponse::fromDomain)
                .toList();
    }
}
