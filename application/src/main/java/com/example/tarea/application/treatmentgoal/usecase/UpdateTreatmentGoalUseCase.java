package com.example.tarea.application.treatmentgoal.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentgoal.command.UpdateTreatmentGoalCommand;
import com.example.tarea.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.example.tarea.domain.treatmentgoal.exception.TreatmentGoalNotFoundException;
import com.example.tarea.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.example.tarea.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class UpdateTreatmentGoalUseCase {

    private final TreatmentGoalRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateTreatmentGoalUseCase(TreatmentGoalRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public TreatmentGoalResponse execute(UpdateTreatmentGoalCommand command) {

        TreatmentGoal treatmentGoal = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentGoalNotFoundException(command.id()));

        treatmentGoal.update(
                command.treatmentPlanId(),
                command.description(),
                command.targetDate(),
                command.completedAt(),
                command.notes(),
                command.treatmentGoalId());

        TreatmentGoal saved = repository.save(treatmentGoal);

        eventPublisher.publish(treatmentGoal.domainEvents());
        treatmentGoal.clearDomainEvents();

        return TreatmentGoalResponse.fromDomain(saved);
    }
}
