package com.example.tarea.application.messagetype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.messagetype.command.RegisterMessageTypeCommand;
import com.example.tarea.application.messagetype.dto.MessageTypeResponse;
import com.example.tarea.domain.messagetype.model.aggregate.MessageType;
import com.example.tarea.domain.messagetype.port.repository.MessageTypeRepository;

public class RegisterMessageTypeUseCase {

    private final MessageTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterMessageTypeUseCase(MessageTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public MessageTypeResponse execute(RegisterMessageTypeCommand command) {

        MessageType messageType = MessageType.register(
                command.nameType());

        MessageType saved = repository.save(messageType);

        eventPublisher.publish(messageType.domainEvents());
        messageType.clearDomainEvents();

        return MessageTypeResponse.fromDomain(saved);
    }
}
