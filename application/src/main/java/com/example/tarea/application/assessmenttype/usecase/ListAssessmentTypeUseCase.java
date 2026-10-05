package com.example.tarea.application.assessmenttype.usecase;

import java.util.List;

import com.example.tarea.application.assessmenttype.dto.AssessmentTypeResponse;
import com.example.tarea.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class ListAssessmentTypeUseCase {

    private final AssessmentTypeRepository repository;

    public ListAssessmentTypeUseCase(AssessmentTypeRepository repository) {
        this.repository = repository;
    }

    public List<AssessmentTypeResponse> execute() {
        return repository.findAll()
                .stream()
                .map(AssessmentTypeResponse::fromDomain)
                .toList();
    }
}
