package com.example.tarea.application.encountertype.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encountertype.command.RegisterEncounterTypeCommand;
import com.example.tarea.application.encountertype.dto.EncounterTypeResponse;
import com.example.tarea.domain.encountertype.model.aggregate.EncounterType;
import com.example.tarea.domain.encountertype.port.repository.EncounterTypeRepository;

public class RegisterEncounterTypeUseCase {

    private final EncounterTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterEncounterTypeUseCase(EncounterTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EncounterTypeResponse execute(RegisterEncounterTypeCommand command) {

        EncounterType encounterType = EncounterType.register(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCode(encounterType.code())) {
            throw new ConflictApplicationException(
                    "A EncounterType with the same code already exists");
        }
        if (repository.existsByName(encounterType.name())) {
            throw new ConflictApplicationException(
                    "A EncounterType with the same name already exists");
        }

        EncounterType saved = repository.save(encounterType);

        eventPublisher.publish(encounterType.domainEvents());
        encounterType.clearDomainEvents();

        return EncounterTypeResponse.fromDomain(saved);
    }
}
