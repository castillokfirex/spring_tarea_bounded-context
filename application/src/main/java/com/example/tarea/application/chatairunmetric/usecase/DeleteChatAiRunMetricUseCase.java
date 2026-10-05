package com.example.tarea.application.chatairunmetric.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatairunmetric.exception.ChatAiRunMetricNotFoundException;
import com.example.tarea.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.example.tarea.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.example.tarea.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class DeleteChatAiRunMetricUseCase {

    private final ChatAiRunMetricRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteChatAiRunMetricUseCase(ChatAiRunMetricRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ChatAiRunMetricId id) {

        ChatAiRunMetric chatAiRunMetric = repository.findById(id)
                .orElseThrow(() -> new ChatAiRunMetricNotFoundException(id));

        chatAiRunMetric.delete();
        repository.delete(chatAiRunMetric);

        eventPublisher.publish(chatAiRunMetric.domainEvents());
        chatAiRunMetric.clearDomainEvents();
    }
}
