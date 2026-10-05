package com.example.tarea.domain.citymunicipality.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.example.tarea.domain.citymunicipality.model.valueobject.CityMunicipalityId;

/**
 * Puerto de salida (output port) para persistir el agregado CityMunicipality.
 */
public interface CityMunicipalityRepository {

    CityMunicipality save(CityMunicipality cityMunicipality);

    Optional<CityMunicipality> findById(CityMunicipalityId id);

    List<CityMunicipality> findAll();

    void delete(CityMunicipality cityMunicipality);
}
