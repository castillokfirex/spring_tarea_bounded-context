package com.example.tarea.domain.encounterstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.example.tarea.domain.encounterstatus.model.valueobject.EncounterStatusId;

/**
 * Puerto de salida (output port) para persistir el agregado EncounterStatus.
 */
public interface EncounterStatusRepository {

    EncounterStatus save(EncounterStatus encounterStatus);

    Optional<EncounterStatus> findById(EncounterStatusId id);

    List<EncounterStatus> findAll();

    void delete(EncounterStatus encounterStatus);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, EncounterStatusId id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, EncounterStatusId id);
}
