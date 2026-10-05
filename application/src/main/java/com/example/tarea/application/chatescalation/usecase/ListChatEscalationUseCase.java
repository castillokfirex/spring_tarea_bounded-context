package com.example.tarea.application.chatescalation.usecase;

import java.util.List;

import com.example.tarea.application.chatescalation.dto.ChatEscalationResponse;
import com.example.tarea.domain.chatescalation.port.repository.ChatEscalationRepository;

public class ListChatEscalationUseCase {

    private final ChatEscalationRepository repository;

    public ListChatEscalationUseCase(ChatEscalationRepository repository) {
        this.repository = repository;
    }

    public List<ChatEscalationResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatEscalationResponse::fromDomain)
                .toList();
    }
}
