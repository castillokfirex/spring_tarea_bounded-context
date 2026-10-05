package com.example.tarea.application.treatmentgoalstatus.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentgoalstatus.command.UpdateTreatmentGoalStatusCommand;
import com.example.tarea.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import com.example.tarea.domain.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundException;
import com.example.tarea.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.example.tarea.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class UpdateTreatmentGoalStatusUseCase {

    private final TreatmentGoalStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public TreatmentGoalStatusResponse execute(UpdateTreatmentGoalStatusCommand command) {

        TreatmentGoalStatus treatmentGoalStatus = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentGoalStatusNotFoundException(command.id()));

        treatmentGoalStatus.update(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCodeAndIdNot(treatmentGoalStatus.code(), treatmentGoalStatus.id())) {
            throw new ConflictApplicationException(
                    "A TreatmentGoalStatus with the same code already exists");
        }
        if (repository.existsByNameAndIdNot(treatmentGoalStatus.name(), treatmentGoalStatus.id())) {
            throw new ConflictApplicationException(
                    "A TreatmentGoalStatus with the same name already exists");
        }

        TreatmentGoalStatus saved = repository.save(treatmentGoalStatus);

        eventPublisher.publish(treatmentGoalStatus.domainEvents());
        treatmentGoalStatus.clearDomainEvents();

        return TreatmentGoalStatusResponse.fromDomain(saved);
    }
}
