package com.example.tarea.application.encountermodality.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encountermodality.command.UpdateEncounterModalityCommand;
import com.example.tarea.application.encountermodality.dto.EncounterModalityResponse;
import com.example.tarea.domain.encountermodality.exception.EncounterModalityNotFoundException;
import com.example.tarea.domain.encountermodality.model.aggregate.EncounterModality;
import com.example.tarea.domain.encountermodality.port.repository.EncounterModalityRepository;

public class UpdateEncounterModalityUseCase {

    private final EncounterModalityRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateEncounterModalityUseCase(EncounterModalityRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EncounterModalityResponse execute(UpdateEncounterModalityCommand command) {

        EncounterModality encounterModality = repository.findById(command.id())
                .orElseThrow(() -> new EncounterModalityNotFoundException(command.id()));

        encounterModality.update(
                command.code(),
                command.name(),
                command.active());

        if (repository.existsByCodeAndIdNot(encounterModality.code(), encounterModality.id())) {
            throw new ConflictApplicationException(
                    "A EncounterModality with the same code already exists");
        }

        EncounterModality saved = repository.save(encounterModality);

        eventPublisher.publish(encounterModality.domainEvents());
        encounterModality.clearDomainEvents();

        return EncounterModalityResponse.fromDomain(saved);
    }
}
