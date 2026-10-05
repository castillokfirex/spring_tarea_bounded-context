package com.example.tarea.application.riskassessment.usecase;

import com.example.tarea.application.riskassessment.dto.RiskAssessmentResponse;
import com.example.tarea.domain.riskassessment.exception.RiskAssessmentNotFoundException;
import com.example.tarea.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.example.tarea.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class GetRiskAssessmentByIdUseCase {

    private final RiskAssessmentRepository repository;

    public GetRiskAssessmentByIdUseCase(RiskAssessmentRepository repository) {
        this.repository = repository;
    }

    public RiskAssessmentResponse execute(RiskAssessmentId id) {
        return repository.findById(id)
                .map(RiskAssessmentResponse::fromDomain)
                .orElseThrow(() -> new RiskAssessmentNotFoundException(id));
    }
}
