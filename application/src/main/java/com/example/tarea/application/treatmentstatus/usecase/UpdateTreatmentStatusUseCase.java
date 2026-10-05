package com.example.tarea.application.treatmentstatus.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentstatus.command.UpdateTreatmentStatusCommand;
import com.example.tarea.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.example.tarea.domain.treatmentstatus.exception.TreatmentStatusNotFoundException;
import com.example.tarea.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.example.tarea.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class UpdateTreatmentStatusUseCase {

    private final TreatmentStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateTreatmentStatusUseCase(TreatmentStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public TreatmentStatusResponse execute(UpdateTreatmentStatusCommand command) {

        TreatmentStatus treatmentStatus = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentStatusNotFoundException(command.id()));

        treatmentStatus.update(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCodeAndIdNot(treatmentStatus.code(), treatmentStatus.id())) {
            throw new ConflictApplicationException(
                    "A TreatmentStatus with the same code already exists");
        }
        if (repository.existsByNameAndIdNot(treatmentStatus.name(), treatmentStatus.id())) {
            throw new ConflictApplicationException(
                    "A TreatmentStatus with the same name already exists");
        }

        TreatmentStatus saved = repository.save(treatmentStatus);

        eventPublisher.publish(treatmentStatus.domainEvents());
        treatmentStatus.clearDomainEvents();

        return TreatmentStatusResponse.fromDomain(saved);
    }
}
