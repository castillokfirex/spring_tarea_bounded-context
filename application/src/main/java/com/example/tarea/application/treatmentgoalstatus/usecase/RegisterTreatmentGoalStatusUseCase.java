package com.example.tarea.application.treatmentgoalstatus.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentgoalstatus.command.RegisterTreatmentGoalStatusCommand;
import com.example.tarea.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.example.tarea.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.example.tarea.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class RegisterTreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public TreatmentGoalStatusResponse execute(RegisterTreatmentGoalStatusCommand command) {

        TreatmentGoalStatus treatmentGoalStatus = TreatmentGoalStatus.register(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCode(treatmentGoalStatus.code())) {
            throw new ConflictApplicationException(
                    "A TreatmentGoalStatus with the same code already exists");
        }
        if (repository.existsByName(treatmentGoalStatus.name())) {
            throw new ConflictApplicationException(
                    "A TreatmentGoalStatus with the same name already exists");
        }

        TreatmentGoalStatus saved = repository.save(treatmentGoalStatus);

        eventPublisher.publish(treatmentGoalStatus.domainEvents());
        treatmentGoalStatus.clearDomainEvents();

        return TreatmentGoalStatusResponse.fromDomain(saved);
    }
}
