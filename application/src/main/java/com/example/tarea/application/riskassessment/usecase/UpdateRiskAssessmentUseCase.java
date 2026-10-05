package com.example.tarea.application.riskassessment.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.riskassessment.command.UpdateRiskAssessmentCommand;
import com.example.tarea.application.riskassessment.dto.RiskAssessmentResponse;
import com.example.tarea.domain.riskassessment.exception.RiskAssessmentNotFoundException;
import com.example.tarea.domain.riskassessment.model.aggregate.RiskAssessment;
import com.example.tarea.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class UpdateRiskAssessmentUseCase {

    private final RiskAssessmentRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateRiskAssessmentUseCase(RiskAssessmentRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public RiskAssessmentResponse execute(UpdateRiskAssessmentCommand command) {

        RiskAssessment riskAssessment = repository.findById(command.id())
                .orElseThrow(() -> new RiskAssessmentNotFoundException(command.id()));

        riskAssessment.update(
                command.encounterId(),
                command.riskLevelId(),
                command.suicidalIdeation(),
                command.suicidePlan(),
                command.suicideIntent(),
                command.selfHarm(),
                command.harmToOthers(),
                command.riskFactors(),
                command.protectiveFactors(),
                command.clinicalActions(),
                command.observations(),
                command.assessedAt(),
                command.assessedBy());

        RiskAssessment saved = repository.save(riskAssessment);

        eventPublisher.publish(riskAssessment.domainEvents());
        riskAssessment.clearDomainEvents();

        return RiskAssessmentResponse.fromDomain(saved);
    }
}
