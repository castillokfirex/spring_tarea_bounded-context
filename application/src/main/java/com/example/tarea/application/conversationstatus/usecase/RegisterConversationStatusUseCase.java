package com.example.tarea.application.conversationstatus.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.conversationstatus.command.RegisterConversationStatusCommand;
import com.example.tarea.application.conversationstatus.dto.ConversationStatusResponse;
import com.example.tarea.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.example.tarea.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class RegisterConversationStatusUseCase {

    private final ConversationStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterConversationStatusUseCase(ConversationStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ConversationStatusResponse execute(RegisterConversationStatusCommand command) {

        ConversationStatus conversationStatus = ConversationStatus.register(
                command.nameStatus());

        ConversationStatus saved = repository.save(conversationStatus);

        eventPublisher.publish(conversationStatus.domainEvents());
        conversationStatus.clearDomainEvents();

        return ConversationStatusResponse.fromDomain(saved);
    }
}
