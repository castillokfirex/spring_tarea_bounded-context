package com.example.tarea.application.treatmentstatus.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentstatus.command.RegisterTreatmentStatusCommand;
import com.example.tarea.application.treatmentstatus.dto.TreatmentStatusResponse;
import com.example.tarea.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.example.tarea.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class RegisterTreatmentStatusUseCase {

    private final TreatmentStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterTreatmentStatusUseCase(TreatmentStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public TreatmentStatusResponse execute(RegisterTreatmentStatusCommand command) {

        TreatmentStatus treatmentStatus = TreatmentStatus.register(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCode(treatmentStatus.code())) {
            throw new ConflictApplicationException(
                    "A TreatmentStatus with the same code already exists");
        }
        if (repository.existsByName(treatmentStatus.name())) {
            throw new ConflictApplicationException(
                    "A TreatmentStatus with the same name already exists");
        }

        TreatmentStatus saved = repository.save(treatmentStatus);

        eventPublisher.publish(treatmentStatus.domainEvents());
        treatmentStatus.clearDomainEvents();

        return TreatmentStatusResponse.fromDomain(saved);
    }
}
