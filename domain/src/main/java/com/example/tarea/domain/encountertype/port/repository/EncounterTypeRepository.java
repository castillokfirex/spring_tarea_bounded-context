package com.example.tarea.domain.encountertype.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.encountertype.model.aggregate.EncounterType;
import com.example.tarea.domain.encountertype.model.valueobject.EncounterTypeId;

/**
 * Puerto de salida (output port) para persistir el agregado EncounterType.
 */
public interface EncounterTypeRepository {

    EncounterType save(EncounterType encounterType);

    Optional<EncounterType> findById(EncounterTypeId id);

    List<EncounterType> findAll();

    void delete(EncounterType encounterType);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, EncounterTypeId id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, EncounterTypeId id);
}
