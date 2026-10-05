package com.example.tarea.domain.treatmentplan.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.example.tarea.domain.treatmentplan.model.valueobject.TreatmentPlanId;

/**
 * Puerto de salida (output port) para persistir el agregado TreatmentPlan.
 */
public interface TreatmentPlanRepository {

    TreatmentPlan save(TreatmentPlan treatmentPlan);

    Optional<TreatmentPlan> findById(TreatmentPlanId id);

    List<TreatmentPlan> findAll();

    void delete(TreatmentPlan treatmentPlan);
}
