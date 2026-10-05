package com.example.tarea.application.country.usecase;

import com.example.tarea.application.country.dto.CountryResponse;
import com.example.tarea.domain.country.exception.CountryNotFoundException;
import com.example.tarea.domain.country.model.valueobject.CountryId;
import com.example.tarea.domain.country.port.repository.CountryRepository;

public class GetCountryByIdUseCase {

    private final CountryRepository repository;

    public GetCountryByIdUseCase(CountryRepository repository) {
        this.repository = repository;
    }

    public CountryResponse execute(CountryId id) {
        return repository.findById(id)
                .map(CountryResponse::fromDomain)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }
}
