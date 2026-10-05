package com.example.tarea.domain.country.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.country.model.aggregate.Country;
import com.example.tarea.domain.country.model.valueobject.CountryId;

/**
 * Puerto de salida (output port) para persistir el agregado Country.
 */
public interface CountryRepository {

    Country save(Country country);

    Optional<Country> findById(CountryId id);

    List<Country> findAll();

    void delete(Country country);
}
