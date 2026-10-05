package com.example.tarea.application.encounterstatus.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encounterstatus.command.RegisterEncounterStatusCommand;
import com.example.tarea.application.encounterstatus.dto.EncounterStatusResponse;
import com.example.tarea.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.example.tarea.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class RegisterEncounterStatusUseCase {

    private final EncounterStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterEncounterStatusUseCase(EncounterStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EncounterStatusResponse execute(RegisterEncounterStatusCommand command) {

        EncounterStatus encounterStatus = EncounterStatus.register(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCode(encounterStatus.code())) {
            throw new ConflictApplicationException(
                    "A EncounterStatus with the same code already exists");
        }
        if (repository.existsByName(encounterStatus.name())) {
            throw new ConflictApplicationException(
                    "A EncounterStatus with the same name already exists");
        }

        EncounterStatus saved = repository.save(encounterStatus);

        eventPublisher.publish(encounterStatus.domainEvents());
        encounterStatus.clearDomainEvents();

        return EncounterStatusResponse.fromDomain(saved);
    }
}
