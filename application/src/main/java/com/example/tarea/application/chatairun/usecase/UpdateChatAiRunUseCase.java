package com.example.tarea.application.chatairun.usecase;

import com.example.tarea.application.chatairun.command.UpdateChatAiRunCommand;
import com.example.tarea.application.chatairun.dto.ChatAiRunResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatairun.exception.ChatAiRunNotFoundException;
import com.example.tarea.domain.chatairun.model.aggregate.ChatAiRun;
import com.example.tarea.domain.chatairun.port.repository.ChatAiRunRepository;

public class UpdateChatAiRunUseCase {

    private final ChatAiRunRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateChatAiRunUseCase(ChatAiRunRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatAiRunResponse execute(UpdateChatAiRunCommand command) {

        ChatAiRun chatAiRun = repository.findById(command.id())
                .orElseThrow(() -> new ChatAiRunNotFoundException(command.id()));

        chatAiRun.update(
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
