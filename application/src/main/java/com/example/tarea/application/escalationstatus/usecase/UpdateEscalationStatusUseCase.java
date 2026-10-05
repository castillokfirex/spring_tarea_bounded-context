package com.example.tarea.application.escalationstatus.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.escalationstatus.command.UpdateEscalationStatusCommand;
import com.example.tarea.application.escalationstatus.dto.EscalationStatusResponse;
import com.example.tarea.domain.escalationstatus.exception.EscalationStatusNotFoundException;
import com.example.tarea.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.example.tarea.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class UpdateEscalationStatusUseCase {

    private final EscalationStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateEscalationStatusUseCase(EscalationStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EscalationStatusResponse execute(UpdateEscalationStatusCommand command) {

        EscalationStatus escalationStatus = repository.findById(command.id())
                .orElseThrow(() -> new EscalationStatusNotFoundException(command.id()));

        escalationStatus.update(
                command.nameStatus());

        EscalationStatus saved = repository.save(escalationStatus);

        eventPublisher.publish(escalationStatus.domainEvents());
        escalationStatus.clearDomainEvents();

        return EscalationStatusResponse.fromDomain(saved);
    }
}
