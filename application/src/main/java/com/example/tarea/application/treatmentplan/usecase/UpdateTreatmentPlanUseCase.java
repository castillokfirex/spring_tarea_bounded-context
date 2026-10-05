package com.example.tarea.application.treatmentplan.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentplan.command.UpdateTreatmentPlanCommand;
import com.example.tarea.application.treatmentplan.dto.TreatmentPlanResponse;
import com.example.tarea.domain.treatmentplan.exception.TreatmentPlanNotFoundException;
import com.example.tarea.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.example.tarea.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class UpdateTreatmentPlanUseCase {

    private final TreatmentPlanRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateTreatmentPlanUseCase(TreatmentPlanRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public TreatmentPlanResponse execute(UpdateTreatmentPlanCommand command) {

        TreatmentPlan treatmentPlan = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentPlanNotFoundException(command.id()));

        treatmentPlan.update(
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
