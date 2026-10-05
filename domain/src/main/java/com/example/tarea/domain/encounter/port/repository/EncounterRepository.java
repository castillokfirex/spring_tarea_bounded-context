package com.example.tarea.domain.encounter.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.encounter.model.aggregate.Encounter;
import com.example.tarea.domain.encounter.model.valueobject.EncounterId;

/**
 * Puerto de salida (output port) para persistir el agregado Encounter.
 */
public interface EncounterRepository {

    Encounter save(Encounter encounter);

    Optional<Encounter> findById(EncounterId id);

    List<Encounter> findAll();

    void delete(Encounter encounter);
}
