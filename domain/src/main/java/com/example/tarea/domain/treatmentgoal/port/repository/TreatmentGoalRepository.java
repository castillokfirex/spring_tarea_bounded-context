package com.example.tarea.domain.treatmentgoal.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.example.tarea.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

/**
 * Puerto de salida (output port) para persistir el agregado TreatmentGoal.
 */
public interface TreatmentGoalRepository {

    TreatmentGoal save(TreatmentGoal treatmentGoal);

    Optional<TreatmentGoal> findById(TreatmentGoalId id);

    List<TreatmentGoal> findAll();

    void delete(TreatmentGoal treatmentGoal);
}
