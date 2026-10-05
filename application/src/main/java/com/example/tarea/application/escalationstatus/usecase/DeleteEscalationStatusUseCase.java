package com.example.tarea.application.escalationstatus.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.escalationstatus.exception.EscalationStatusNotFoundException;
import com.example.tarea.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.example.tarea.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.example.tarea.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class DeleteEscalationStatusUseCase {

    private final EscalationStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteEscalationStatusUseCase(EscalationStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(EscalationStatusId id) {

        EscalationStatus escalationStatus = repository.findById(id)
                .orElseThrow(() -> new EscalationStatusNotFoundException(id));

        escalationStatus.delete();
        repository.delete(escalationStatus);

        eventPublisher.publish(escalationStatus.domainEvents());
        escalationStatus.clearDomainEvents();
    }
}
