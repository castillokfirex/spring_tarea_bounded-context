package com.example.tarea.application.chatescalationstatushistory.usecase;

import com.example.tarea.application.chatescalationstatushistory.command.RegisterChatEscalationStatusHistoryCommand;
import com.example.tarea.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.example.tarea.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class RegisterChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatEscalationStatusHistoryResponse execute(RegisterChatEscalationStatusHistoryCommand command) {

        ChatEscalationStatusHistory chatEscalationStatusHistory = ChatEscalationStatusHistory.register(
                command.escalationId(),
                command.escalationStatusId(),
                command.changedAt());

        ChatEscalationStatusHistory saved = repository.save(chatEscalationStatusHistory);

        eventPublisher.publish(chatEscalationStatusHistory.domainEvents());
        chatEscalationStatusHistory.clearDomainEvents();

        return ChatEscalationStatusHistoryResponse.fromDomain(saved);
    }
}
