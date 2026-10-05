package com.example.tarea.application.chatescalation.usecase;

import com.example.tarea.application.chatescalation.dto.ChatEscalationResponse;
import com.example.tarea.domain.chatescalation.exception.ChatEscalationNotFoundException;
import com.example.tarea.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.example.tarea.domain.chatescalation.port.repository.ChatEscalationRepository;

public class GetChatEscalationByIdUseCase {

    private final ChatEscalationRepository repository;

    public GetChatEscalationByIdUseCase(ChatEscalationRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationResponse execute(ChatEscalationId id) {
        return repository.findById(id)
                .map(ChatEscalationResponse::fromDomain)
                .orElseThrow(() -> new ChatEscalationNotFoundException(id));
    }
}
