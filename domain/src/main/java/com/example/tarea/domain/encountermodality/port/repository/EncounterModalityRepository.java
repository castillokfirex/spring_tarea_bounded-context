package com.example.tarea.domain.encountermodality.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.encountermodality.model.aggregate.EncounterModality;
import com.example.tarea.domain.encountermodality.model.valueobject.EncounterModalityId;

/**
 * Puerto de salida (output port) para persistir el agregado EncounterModality.
 */
public interface EncounterModalityRepository {

    EncounterModality save(EncounterModality encounterModality);

    Optional<EncounterModality> findById(EncounterModalityId id);

    List<EncounterModality> findAll();

    void delete(EncounterModality encounterModality);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, EncounterModalityId id);
}
