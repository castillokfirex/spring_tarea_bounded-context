package com.example.tarea.application.encounter.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encounter.command.UpdateEncounterCommand;
import com.example.tarea.application.encounter.dto.EncounterResponse;
import com.example.tarea.domain.encounter.exception.EncounterNotFoundException;
import com.example.tarea.domain.encounter.model.aggregate.Encounter;
import com.example.tarea.domain.encounter.port.repository.EncounterRepository;

public class UpdateEncounterUseCase {

    private final EncounterRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateEncounterUseCase(EncounterRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EncounterResponse execute(UpdateEncounterCommand command) {

        Encounter encounter = repository.findById(command.id())
                .orElseThrow(() -> new EncounterNotFoundException(command.id()));

        encounter.update(
                command.clinicalRecordId(),
                command.professionalId(),
                command.encounterTypeId(),
                command.startedAt(),
                command.endedAt(),
                command.reasonForVisit(),
                command.currentCondition(),
                command.modalityId(),
                command.statusId(),
                command.updatedBy());

        Encounter saved = repository.save(encounter);

        eventPublisher.publish(encounter.domainEvents());
        encounter.clearDomainEvents();

        return EncounterResponse.fromDomain(saved);
    }
}
