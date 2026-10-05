package com.example.tarea.application.chatescalationstatushistory.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundException;
import com.example.tarea.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.example.tarea.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.example.tarea.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class DeleteChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ChatEscalationStatusHistoryId id) {

        ChatEscalationStatusHistory chatEscalationStatusHistory = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationStatusHistoryNotFoundException(id));

        chatEscalationStatusHistory.delete();
        repository.delete(chatEscalationStatusHistory);

        eventPublisher.publish(chatEscalationStatusHistory.domainEvents());
        chatEscalationStatusHistory.clearDomainEvents();
    }
}
