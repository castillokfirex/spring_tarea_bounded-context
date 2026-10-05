package com.example.tarea.application.chatairun.usecase;

import com.example.tarea.application.chatairun.command.RegisterChatAiRunCommand;
import com.example.tarea.application.chatairun.dto.ChatAiRunResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatairun.model.aggregate.ChatAiRun;
import com.example.tarea.domain.chatairun.port.repository.ChatAiRunRepository;

public class RegisterChatAiRunUseCase {

    private final ChatAiRunRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterChatAiRunUseCase(ChatAiRunRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatAiRunResponse execute(RegisterChatAiRunCommand command) {

        ChatAiRun chatAiRun = ChatAiRun.register(
                command.conversationId(),
                command.messageId(),
                command.modelId(),
                command.aiRunStatusId());

        ChatAiRun saved = repository.save(chatAiRun);

        eventPublisher.publish(chatAiRun.domainEvents());
        chatAiRun.clearDomainEvents();

        return ChatAiRunResponse.fromDomain(saved);
    }
}
