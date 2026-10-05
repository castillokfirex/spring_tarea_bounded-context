package com.example.tarea.application.encounter.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encounter.command.RegisterEncounterCommand;
import com.example.tarea.application.encounter.dto.EncounterResponse;
import com.example.tarea.domain.encounter.model.aggregate.Encounter;
import com.example.tarea.domain.encounter.port.repository.EncounterRepository;

public class RegisterEncounterUseCase {

    private final EncounterRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterEncounterUseCase(EncounterRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EncounterResponse execute(RegisterEncounterCommand command) {

        Encounter encounter = Encounter.register(
                command.clinicalRecordId(),
                command.professionalId(),
                command.encounterTypeId(),
                command.startedAt(),
                command.endedAt(),
                command.reasonForVisit(),
                command.currentCondition(),
                command.modalityId(),
                command.statusId(),
                command.createdBy(),
                command.updatedBy());

        Encounter saved = repository.save(encounter);

        eventPublisher.publish(encounter.domainEvents());
        encounter.clearDomainEvents();

        return EncounterResponse.fromDomain(saved);
    }
}
