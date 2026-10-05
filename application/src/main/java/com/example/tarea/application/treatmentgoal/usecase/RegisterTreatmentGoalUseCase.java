package com.example.tarea.application.treatmentgoal.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentgoal.command.RegisterTreatmentGoalCommand;
import com.example.tarea.application.treatmentgoal.dto.TreatmentGoalResponse;
import com.example.tarea.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.example.tarea.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class RegisterTreatmentGoalUseCase {

    private final TreatmentGoalRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterTreatmentGoalUseCase(TreatmentGoalRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public TreatmentGoalResponse execute(RegisterTreatmentGoalCommand command) {

        TreatmentGoal treatmentGoal = TreatmentGoal.register(
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
