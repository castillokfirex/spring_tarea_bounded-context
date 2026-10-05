package com.example.tarea.application.sendertype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.sendertype.exception.SenderTypeNotFoundException;
import com.example.tarea.domain.sendertype.model.aggregate.SenderType;
import com.example.tarea.domain.sendertype.model.valueobject.SenderTypeId;
import com.example.tarea.domain.sendertype.port.repository.SenderTypeRepository;

public class DeleteSenderTypeUseCase {

    private final SenderTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteSenderTypeUseCase(SenderTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(SenderTypeId id) {

        SenderType senderType = repository.findById(id)
                .orElseThrow(() -> new SenderTypeNotFoundException(id));

        senderType.delete();
        repository.delete(senderType);

        eventPublisher.publish(senderType.domainEvents());
        senderType.clearDomainEvents();
    }
}
