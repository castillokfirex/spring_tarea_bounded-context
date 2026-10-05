package com.example.tarea.application.chatairunerror.usecase;

import com.example.tarea.application.chatairunerror.command.RegisterChatAiRunErrorCommand;
import com.example.tarea.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.example.tarea.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class RegisterChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterChatAiRunErrorUseCase(ChatAiRunErrorRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatAiRunErrorResponse execute(RegisterChatAiRunErrorCommand command) {

        ChatAiRunError chatAiRunError = ChatAiRunError.register(
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
