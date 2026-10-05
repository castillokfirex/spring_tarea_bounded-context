package com.example.tarea.application.chatairun.usecase;

import com.example.tarea.application.chatairun.dto.ChatAiRunResponse;
import com.example.tarea.domain.chatairun.exception.ChatAiRunNotFoundException;
import com.example.tarea.domain.chatairun.model.valueobject.ChatAiRunId;
import com.example.tarea.domain.chatairun.port.repository.ChatAiRunRepository;

public class GetChatAiRunByIdUseCase {

    private final ChatAiRunRepository repository;

    public GetChatAiRunByIdUseCase(ChatAiRunRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunResponse execute(ChatAiRunId id) {
        return repository.findById(id)
                .map(ChatAiRunResponse::fromDomain)
                .orElseThrow(() -> new ChatAiRunNotFoundException(id));
    }
}
