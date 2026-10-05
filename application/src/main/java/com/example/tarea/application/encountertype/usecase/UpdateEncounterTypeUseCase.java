package com.example.tarea.application.encountertype.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encountertype.command.UpdateEncounterTypeCommand;
import com.example.tarea.application.encountertype.dto.EncounterTypeResponse;
import com.example.tarea.domain.encountertype.exception.EncounterTypeNotFoundException;
import com.example.tarea.domain.encountertype.model.aggregate.EncounterType;
import com.example.tarea.domain.encountertype.port.repository.EncounterTypeRepository;

public class UpdateEncounterTypeUseCase {

    private final EncounterTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateEncounterTypeUseCase(EncounterTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EncounterTypeResponse execute(UpdateEncounterTypeCommand command) {

        EncounterType encounterType = repository.findById(command.id())
                .orElseThrow(() -> new EncounterTypeNotFoundException(command.id()));

        encounterType.update(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCodeAndIdNot(encounterType.code(), encounterType.id())) {
            throw new ConflictApplicationException(
                    "A EncounterType with the same code already exists");
        }
        if (repository.existsByNameAndIdNot(encounterType.name(), encounterType.id())) {
            throw new ConflictApplicationException(
                    "A EncounterType with the same name already exists");
        }

        EncounterType saved = repository.save(encounterType);

        eventPublisher.publish(encounterType.domainEvents());
        encounterType.clearDomainEvents();

        return EncounterTypeResponse.fromDomain(saved);
    }
}
