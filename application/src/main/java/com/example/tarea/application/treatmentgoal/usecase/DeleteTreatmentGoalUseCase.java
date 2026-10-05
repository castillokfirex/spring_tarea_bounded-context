package com.example.tarea.application.treatmentgoal.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.treatmentgoal.exception.TreatmentGoalNotFoundException;
import com.example.tarea.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.example.tarea.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.example.tarea.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class DeleteTreatmentGoalUseCase {

    private final TreatmentGoalRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteTreatmentGoalUseCase(TreatmentGoalRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(TreatmentGoalId id) {

        TreatmentGoal treatmentGoal = repository.findById(id)
                .orElseThrow(() -> new TreatmentGoalNotFoundException(id));

        treatmentGoal.delete();
        repository.delete(treatmentGoal);

        eventPublisher.publish(treatmentGoal.domainEvents());
        treatmentGoal.clearDomainEvents();
    }
}
