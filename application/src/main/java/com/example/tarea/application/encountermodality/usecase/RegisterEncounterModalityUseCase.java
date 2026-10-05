package com.example.tarea.application.encountermodality.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encountermodality.command.RegisterEncounterModalityCommand;
import com.example.tarea.application.encountermodality.dto.EncounterModalityResponse;
import com.example.tarea.domain.encountermodality.model.aggregate.EncounterModality;
import com.example.tarea.domain.encountermodality.port.repository.EncounterModalityRepository;

public class RegisterEncounterModalityUseCase {

    private final EncounterModalityRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterEncounterModalityUseCase(EncounterModalityRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EncounterModalityResponse execute(RegisterEncounterModalityCommand command) {

        EncounterModality encounterModality = EncounterModality.register(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCode(encounterModality.code())) {
            throw new ConflictApplicationException(
                    "A EncounterModality with the same code already exists");
        }

        EncounterModality saved = repository.save(encounterModality);

        eventPublisher.publish(encounterModality.domainEvents());
        encounterModality.clearDomainEvents();

        return EncounterModalityResponse.fromDomain(saved);
    }
}
