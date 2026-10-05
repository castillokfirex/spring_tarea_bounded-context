package com.example.tarea.domain.assessmenttype.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.assessmenttype.model.aggregate.AssessmentType;
import com.example.tarea.domain.assessmenttype.model.valueobject.AssessmentTypeId;

/**
 * Puerto de salida (output port) para persistir el agregado AssessmentType.
 */
public interface AssessmentTypeRepository {

    AssessmentType save(AssessmentType assessmentType);

    Optional<AssessmentType> findById(AssessmentTypeId id);

    List<AssessmentType> findAll();

    void delete(AssessmentType assessmentType);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, AssessmentTypeId id);
}
