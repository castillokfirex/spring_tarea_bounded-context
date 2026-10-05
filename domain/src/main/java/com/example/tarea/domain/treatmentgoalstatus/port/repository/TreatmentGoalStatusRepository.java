package com.example.tarea.domain.treatmentgoalstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.example.tarea.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

/**
 * Puerto de salida (output port) para persistir el agregado TreatmentGoalStatus.
 */
public interface TreatmentGoalStatusRepository {

    TreatmentGoalStatus save(TreatmentGoalStatus treatmentGoalStatus);

    Optional<TreatmentGoalStatus> findById(TreatmentGoalStatusId id);

    List<TreatmentGoalStatus> findAll();

    void delete(TreatmentGoalStatus treatmentGoalStatus);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, TreatmentGoalStatusId id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, TreatmentGoalStatusId id);
}
