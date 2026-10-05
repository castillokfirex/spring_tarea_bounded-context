package com.example.tarea.application.treatmentplan.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.treatmentplan.exception.TreatmentPlanNotFoundException;
import com.example.tarea.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.example.tarea.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.example.tarea.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class DeleteTreatmentPlanUseCase {

    private final TreatmentPlanRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteTreatmentPlanUseCase(TreatmentPlanRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(TreatmentPlanId id) {

        TreatmentPlan treatmentPlan = repository.findById(id)
                .orElseThrow(() -> new TreatmentPlanNotFoundException(id));

        treatmentPlan.delete();
        repository.delete(treatmentPlan);

        eventPublisher.publish(treatmentPlan.domainEvents());
        treatmentPlan.clearDomainEvents();
    }
}
