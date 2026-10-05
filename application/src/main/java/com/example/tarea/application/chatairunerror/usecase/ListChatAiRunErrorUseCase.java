package com.example.tarea.application.chatairunerror.usecase;

import java.util.List;

import com.example.tarea.application.chatairunerror.dto.ChatAiRunErrorResponse;
import com.example.tarea.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;

public class ListChatAiRunErrorUseCase {

    private final ChatAiRunErrorRepository repository;

    public ListChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) {
        this.repository = repository;
    }

    public List<ChatAiRunErrorResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatAiRunErrorResponse::fromDomain)
                .toList();
    }
}
