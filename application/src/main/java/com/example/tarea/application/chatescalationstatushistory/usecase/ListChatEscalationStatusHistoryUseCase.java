package com.example.tarea.application.chatescalationstatushistory.usecase;

import java.util.List;

import com.example.tarea.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.example.tarea.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class ListChatEscalationStatusHistoryUseCase {

    private final ChatEscalationStatusHistoryRepository repository;

    public ListChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) {
        this.repository = repository;
    }

    public List<ChatEscalationStatusHistoryResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatEscalationStatusHistoryResponse::fromDomain)
                .toList();
    }
}
