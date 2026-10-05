package com.example.tarea.application.chatescalationstatushistory.usecase;

import com.example.tarea.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.example.tarea.domain.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundException;
import com.example.tarea.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.example.tarea.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class GetChatEscalationStatusHistoryByIdUseCase {

    private final ChatEscalationStatusHistoryRepository repository;

    public GetChatEscalationStatusHistoryByIdUseCase(ChatEscalationStatusHistoryRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationStatusHistoryResponse execute(ChatEscalationStatusHistoryId id) {
        return repository.findById(id)
                .map(ChatEscalationStatusHistoryResponse::fromDomain)
                .orElseThrow(() -> new ChatEscalationStatusHistoryNotFoundException(id));
    }
}
