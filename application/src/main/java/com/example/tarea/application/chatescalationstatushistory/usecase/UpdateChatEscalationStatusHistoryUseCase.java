package com.example.tarea.application.chatescalationstatushistory.usecase;

import com.example.tarea.application.chatescalationstatushistory.command.UpdateChatEscalationStatusHistoryCommand;
import com.example.tarea.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundException;
import com.example.tarea.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.example.tarea.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class UpdateChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatEscalationStatusHistoryResponse execute(UpdateChatEscalationStatusHistoryCommand command) {

        ChatEscalationStatusHistory chatEscalationStatusHistory = repository.findById(command.id())
                .orElseThrow(() -> new ChatEscalationStatusHistoryNotFoundException(command.id()));

        chatEscalationStatusHistory.update(
                command.escalationId(),
                command.escalationStatusId(),
                command.changedAt());

        ChatEscalationStatusHistory saved = repository.save(chatEscalationStatusHistory);

        eventPublisher.publish(chatEscalationStatusHistory.domainEvents());
        chatEscalationStatusHistory.clearDomainEvents();

        return ChatEscalationStatusHistoryResponse.fromDomain(saved);
    }
}
