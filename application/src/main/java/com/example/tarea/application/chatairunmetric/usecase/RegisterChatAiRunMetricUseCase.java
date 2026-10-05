package com.example.tarea.application.chatairunmetric.usecase;

import com.example.tarea.application.chatairunmetric.command.RegisterChatAiRunMetricCommand;
import com.example.tarea.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.example.tarea.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class RegisterChatAiRunMetricUseCase {

    private final ChatAiRunMetricRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterChatAiRunMetricUseCase(ChatAiRunMetricRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatAiRunMetricResponse execute(RegisterChatAiRunMetricCommand command) {

        ChatAiRunMetric chatAiRunMetric = ChatAiRunMetric.register(
                command.aiRunId(),
                command.promptTokens(),
                command.completionTokens(),
                command.totalTokens(),
                command.cost());

        ChatAiRunMetric saved = repository.save(chatAiRunMetric);

        eventPublisher.publish(chatAiRunMetric.domainEvents());
        chatAiRunMetric.clearDomainEvents();

        return ChatAiRunMetricResponse.fromDomain(saved);
    }
}
