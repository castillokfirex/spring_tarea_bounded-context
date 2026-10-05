package com.example.tarea.application.chatairunerror.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatairunerror.exception.ChatAiRunErrorNotFoundException;
import com.example.tarea.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.example.tarea.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.example.tarea.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class DeleteChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteChatAiRunErrorUseCase(ChatAiRunErrorRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ChatAiRunErrorId id) {

        ChatAiRunError chatAiRunError = repository.findById(id)
                .orElseThrow(() -> new ChatAiRunErrorNotFoundException(id));

        chatAiRunError.delete();
        repository.delete(chatAiRunError);

        eventPublisher.publish(chatAiRunError.domainEvents());
        chatAiRunError.clearDomainEvents();
    }
}
