package com.example.tarea.domain.stateregion.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.stateregion.model.aggregate.StateRegion;
import com.example.tarea.domain.stateregion.model.valueobject.StateRegionId;

/**
 * Puerto de salida (output port) para persistir el agregado StateRegion.
 */
public interface StateRegionRepository {

    StateRegion save(StateRegion stateRegion);

    Optional<StateRegion> findById(StateRegionId id);

    List<StateRegion> findAll();

    void delete(StateRegion stateRegion);
}
