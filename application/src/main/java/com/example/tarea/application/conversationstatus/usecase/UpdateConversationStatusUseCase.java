package com.example.tarea.application.conversationstatus.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.conversationstatus.command.UpdateConversationStatusCommand;
import com.example.tarea.application.conversationstatus.dto.ConversationStatusResponse;
import com.example.tarea.domain.conversationstatus.exception.ConversationStatusNotFoundException;
import com.example.tarea.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.example.tarea.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class UpdateConversationStatusUseCase {

    private final ConversationStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateConversationStatusUseCase(ConversationStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ConversationStatusResponse execute(UpdateConversationStatusCommand command) {

        ConversationStatus conversationStatus = repository.findById(command.id())
                .orElseThrow(() -> new ConversationStatusNotFoundException(command.id()));

        conversationStatus.update(
                command.nameStatus());

        ConversationStatus saved = repository.save(conversationStatus);

        eventPublisher.publish(conversationStatus.domainEvents());
        conversationStatus.clearDomainEvents();

        return ConversationStatusResponse.fromDomain(saved);
    }
}
