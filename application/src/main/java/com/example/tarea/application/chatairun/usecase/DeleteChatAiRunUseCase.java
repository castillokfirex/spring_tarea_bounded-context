package com.example.tarea.application.chatairun.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatairun.exception.ChatAiRunNotFoundException;
import com.example.tarea.domain.chatairun.model.aggregate.ChatAiRun;
import com.example.tarea.domain.chatairun.model.valueobject.ChatAiRunId;
import com.example.tarea.domain.chatairun.port.repository.ChatAiRunRepository;

public class DeleteChatAiRunUseCase {

    private final ChatAiRunRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteChatAiRunUseCase(ChatAiRunRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ChatAiRunId id) {

        ChatAiRun chatAiRun = repository.findById(id)
                .orElseThrow(() -> new ChatAiRunNotFoundException(id));

        chatAiRun.delete();
        repository.delete(chatAiRun);

        eventPublisher.publish(chatAiRun.domainEvents());
        chatAiRun.clearDomainEvents();
    }
}
