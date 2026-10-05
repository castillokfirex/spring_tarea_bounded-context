package com.example.tarea.application.riskassessment.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.riskassessment.command.RegisterRiskAssessmentCommand;
import com.example.tarea.application.riskassessment.dto.RiskAssessmentResponse;
import com.example.tarea.domain.riskassessment.model.aggregate.RiskAssessment;
import com.example.tarea.domain.riskassessment.port.repository.RiskAssessmentRepository;

public class RegisterRiskAssessmentUseCase {

    private final RiskAssessmentRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterRiskAssessmentUseCase(RiskAssessmentRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public RiskAssessmentResponse execute(RegisterRiskAssessmentCommand command) {

        RiskAssessment riskAssessment = RiskAssessment.register(
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
