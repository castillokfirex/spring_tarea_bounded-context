package com.example.tarea.application.messagetype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.messagetype.command.UpdateMessageTypeCommand;
import com.example.tarea.application.messagetype.dto.MessageTypeResponse;
import com.example.tarea.domain.messagetype.exception.MessageTypeNotFoundException;
import com.example.tarea.domain.messagetype.model.aggregate.MessageType;
import com.example.tarea.domain.messagetype.port.repository.MessageTypeRepository;

public class UpdateMessageTypeUseCase {

    private final MessageTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateMessageTypeUseCase(MessageTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public MessageTypeResponse execute(UpdateMessageTypeCommand command) {

        MessageType messageType = repository.findById(command.id())
                .orElseThrow(() -> new MessageTypeNotFoundException(command.id()));

        messageType.update(
                command.nameType());

        MessageType saved = repository.save(messageType);

        eventPublisher.publish(messageType.domainEvents());
        messageType.clearDomainEvents();

        return MessageTypeResponse.fromDomain(saved);
    }
}
