package com.example.tarea.application.chatairunerror.usecase;

import com.example.tarea.application.chatairunerror.command.UpdateChatAiRunErrorCommand;
import com.example.tarea.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatairunerror.exception.ChatAiRunErrorNotFoundException;
import com.example.tarea.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.example.tarea.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class UpdateChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateChatAiRunErrorUseCase(ChatAiRunErrorRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatAiRunErrorResponse execute(UpdateChatAiRunErrorCommand command) {

        ChatAiRunError chatAiRunError = repository.findById(command.id())
                .orElseThrow(() -> new ChatAiRunErrorNotFoundException(command.id()));

        chatAiRunError.update(
                command.aiRunId(),
                command.errorMessage(),
                command.errorCode(),
                command.providerErrorId());

        ChatAiRunError saved = repository.save(chatAiRunError);

        eventPublisher.publish(chatAiRunError.domainEvents());
        chatAiRunError.clearDomainEvents();

        return ChatAiRunErrorResponse.fromDomain(saved);
    }
}
