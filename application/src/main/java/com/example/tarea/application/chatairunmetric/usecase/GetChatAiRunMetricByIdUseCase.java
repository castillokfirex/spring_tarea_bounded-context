package com.example.tarea.application.chatairunmetric.usecase;

import com.example.tarea.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import com.example.tarea.domain.chatairunmetric.exception.ChatAiRunMetricNotFoundException;
import com.example.tarea.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.example.tarea.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class GetChatAiRunMetricByIdUseCase {

    private final ChatAiRunMetricRepository repository;

    public GetChatAiRunMetricByIdUseCase(ChatAiRunMetricRepository repository) {
        this.repository = repository;
    }

    public ChatAiRunMetricResponse execute(ChatAiRunMetricId id) {
        return repository.findById(id)
                .map(ChatAiRunMetricResponse::fromDomain)
                .orElseThrow(() -> new ChatAiRunMetricNotFoundException(id));
    }
}
