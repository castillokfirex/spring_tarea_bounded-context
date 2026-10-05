package com.example.tarea.application.conversationstatus.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.conversationstatus.exception.ConversationStatusNotFoundException;
import com.example.tarea.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.example.tarea.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.example.tarea.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class DeleteConversationStatusUseCase {

    private final ConversationStatusRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteConversationStatusUseCase(ConversationStatusRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ConversationStatusId id) {

        ConversationStatus conversationStatus = repository.findById(id)
                .orElseThrow(() -> new ConversationStatusNotFoundException(id));

        conversationStatus.delete();
        repository.delete(conversationStatus);

        eventPublisher.publish(conversationStatus.domainEvents());
        conversationStatus.clearDomainEvents();
    }
}
