package com.example.tarea.application.messagetype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.messagetype.exception.MessageTypeNotFoundException;
import com.example.tarea.domain.messagetype.model.aggregate.MessageType;
import com.example.tarea.domain.messagetype.model.valueobject.MessageTypeId;
import com.example.tarea.domain.messagetype.port.repository.MessageTypeRepository;

public class DeleteMessageTypeUseCase {

    private final MessageTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteMessageTypeUseCase(MessageTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(MessageTypeId id) {

        MessageType messageType = repository.findById(id)
                .orElseThrow(() -> new MessageTypeNotFoundException(id));

        messageType.delete();
        repository.delete(messageType);

        eventPublisher.publish(messageType.domainEvents());
        messageType.clearDomainEvents();
    }
}
