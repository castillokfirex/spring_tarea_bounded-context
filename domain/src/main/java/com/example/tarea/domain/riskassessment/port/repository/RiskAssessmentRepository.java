package com.example.tarea.domain.riskassessment.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.riskassessment.model.aggregate.RiskAssessment;
import com.example.tarea.domain.riskassessment.model.valueobject.RiskAssessmentId;

/**
 * Puerto de salida (output port) para persistir el agregado RiskAssessment.
 */
public interface RiskAssessmentRepository {

    RiskAssessment save(RiskAssessment riskAssessment);

    Optional<RiskAssessment> findById(RiskAssessmentId id);

    List<RiskAssessment> findAll();

    void delete(RiskAssessment riskAssessment);
}
