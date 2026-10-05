package com.example.tarea.application.riskassessment.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.riskassessment.exception.RiskAssessmentNotFoundException;
import com.example.tarea.domain.riskassessment.model.aggregate.RiskAssessment;
import com.example.tarea.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.example.tarea.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class DeleteRiskAssessmentUseCase {

    private final RiskAssessmentRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteRiskAssessmentUseCase(RiskAssessmentRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(RiskAssessmentId id) {

        RiskAssessment riskAssessment = repository.findById(id)
                .orElseThrow(() -> new RiskAssessmentNotFoundException(id));

        riskAssessment.delete();
        repository.delete(riskAssessment);

        eventPublisher.publish(riskAssessment.domainEvents());
        riskAssessment.clearDomainEvents();
    }
}
