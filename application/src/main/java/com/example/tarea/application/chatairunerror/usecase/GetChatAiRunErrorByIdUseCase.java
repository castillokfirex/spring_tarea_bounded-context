package com.example.tarea.application.chatairunerror.usecase;

import com.example.tarea.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.example.tarea.domain.chatairunerror.exception.ChatAiRunErrorNotFoundException;
import com.example.tarea.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.example.tarea.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class GetChatAiRunErrorByIdUseCase {

    private final ChatAiRunErrorRepository repository;

    public GetChatAiRunErrorByIdUseCase(ChatAiRunErrorRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunErrorResponse execute(ChatAiRunErrorId id) {
        return repository.findById(id)
                .map(ChatAiRunErrorResponse::fromDomain)
                .orElseThrow(() -> new ChatAiRunErrorNotFoundException(id));
    }
}
