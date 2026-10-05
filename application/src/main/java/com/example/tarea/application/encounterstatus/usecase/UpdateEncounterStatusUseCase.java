package com.example.tarea.application.encounterstatus.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encounterstatus.command.UpdateEncounterStatusCommand;
import com.example.tarea.application.encounterstatus.dto.EncounterStatusResponse;
import com.example.tarea.domain.encounterstatus.exception.EncounterStatusNotFoundException;
import com.example.tarea.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.example.tarea.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class UpdateEncounterStatusUseCase {

    private final EncounterStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateEncounterStatusUseCase(EncounterStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EncounterStatusResponse execute(UpdateEncounterStatusCommand command) {

        EncounterStatus encounterStatus = repository.findById(command.id())
                .orElseThrow(() -> new EncounterStatusNotFoundException(command.id()));

        encounterStatus.update(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCodeAndIdNot(encounterStatus.code(), encounterStatus.id())) {
            throw new ConflictApplicationException(
                    "A EncounterStatus with the same code already exists");
        }
        if (repository.existsByNameAndIdNot(encounterStatus.name(), encounterStatus.id())) {
            throw new ConflictApplicationException(
                    "A EncounterStatus with the same name already exists");
        }

        EncounterStatus saved = repository.save(encounterStatus);

        eventPublisher.publish(encounterStatus.domainEvents());
        encounterStatus.clearDomainEvents();

        return EncounterStatusResponse.fromDomain(saved);
    }
}
