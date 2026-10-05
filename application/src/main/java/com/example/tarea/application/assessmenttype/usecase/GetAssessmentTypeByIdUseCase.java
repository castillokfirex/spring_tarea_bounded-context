package com.example.tarea.application.assessmenttype.usecase;

import com.example.tarea.application.assessmenttype.dto.AssessmentTypeResponse;
import com.example.tarea.domain.assessmenttype.exception.AssessmentTypeNotFoundException;
import com.example.tarea.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.example.tarea.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class GetAssessmentTypeByIdUseCase {

    private final AssessmentTypeRepository repository;

    public GetAssessmentTypeByIdUseCase(AssessmentTypeRepository repository) {
        this.repository = repository;
    }

    public AssessmentTypeResponse execute(AssessmentTypeId id) {
        return repository.findById(id)
                .map(AssessmentTypeResponse::fromDomain)
                .orElseThrow(() -> new AssessmentTypeNotFoundException(id));
    }
}
