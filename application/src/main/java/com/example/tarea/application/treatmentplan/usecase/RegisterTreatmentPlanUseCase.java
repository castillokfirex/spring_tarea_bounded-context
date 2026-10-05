package com.example.tarea.application.treatmentplan.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentplan.command.RegisterTreatmentPlanCommand;
import com.example.tarea.application.treatmentplan.dto.TreatmentPlanResponse;
import com.example.tarea.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.example.tarea.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class RegisterTreatmentPlanUseCase {

    private final TreatmentPlanRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterTreatmentPlanUseCase(TreatmentPlanRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public TreatmentPlanResponse execute(RegisterTreatmentPlanCommand command) {

        TreatmentPlan treatmentPlan = TreatmentPlan.register(
                command.encounterId(),
                command.professionalId(),
                command.title(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.treatmentStatusId());

        TreatmentPlan saved = repository.save(treatmentPlan);

        eventPublisher.publish(treatmentPlan.domainEvents());
        treatmentPlan.clearDomainEvents();

        return TreatmentPlanResponse.fromDomain(saved);
    }
}
