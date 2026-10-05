package com.example.tarea.application.escalationstatus.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.escalationstatus.command.RegisterEscalationStatusCommand;
import com.example.tarea.application.escalationstatus.dto.EscalationStatusResponse;
import com.example.tarea.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.example.tarea.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class RegisterEscalationStatusUseCase {

    private final EscalationStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterEscalationStatusUseCase(EscalationStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public EscalationStatusResponse execute(RegisterEscalationStatusCommand command) {

        EscalationStatus escalationStatus = EscalationStatus.register(
                command.nameStatus());

        EscalationStatus saved = repository.save(escalationStatus);

        eventPublisher.publish(escalationStatus.domainEvents());
        escalationStatus.clearDomainEvents();

        return EscalationStatusResponse.fromDomain(saved);
    }
}
