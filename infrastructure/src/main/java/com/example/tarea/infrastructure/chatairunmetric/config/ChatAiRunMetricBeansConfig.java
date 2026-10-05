package com.example.tarea.infrastructure.chatairunmetric.config;

import com.example.tarea.application.chatairunmetric.usecase.DeleteChatAiRunMetricUseCase;
import com.example.tarea.application.chatairunmetric.usecase.GetChatAiRunMetricByIdUseCase;
import com.example.tarea.application.chatairunmetric.usecase.ListChatAiRunMetricUseCase;
import com.example.tarea.application.chatairunmetric.usecase.RegisterChatAiRunMetricUseCase;
import com.example.tarea.application.chatairunmetric.usecase.UpdateChatAiRunMetricUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;
import com.example.tarea.infrastructure.chatairunmetric.adapters.out.persistence.mappers.ChatAiRunMetricPersistenceMapper;
import com.example.tarea.infrastructure.chatairunmetric.adapters.out.persistence.repositories.ChatAiRunMetricJpaRepository;
import com.example.tarea.infrastructure.chatairunmetric.adapters.out.persistence.repositories.ChatAiRunMetricRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context chatairunmetric: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ChatAiRunMetricBeansConfig {

    @Bean
    public ChatAiRunMetricPersistenceMapper chatAiRunMetricPersistenceMapper() {
        return new ChatAiRunMetricPersistenceMapper();
    }

    @Bean
    public ChatAiRunMetricRepository chatAiRunMetricRepository(ChatAiRunMetricJpaRepository jpaRepository, ChatAiRunMetricPersistenceMapper mapper) {
        return new ChatAiRunMetricRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterChatAiRunMetricUseCase registerChatAiRunMetricUseCase(ChatAiRunMetricRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterChatAiRunMetricUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateChatAiRunMetricUseCase updateChatAiRunMetricUseCase(ChatAiRunMetricRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateChatAiRunMetricUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteChatAiRunMetricUseCase deleteChatAiRunMetricUseCase(ChatAiRunMetricRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteChatAiRunMetricUseCase(repository, eventPublisher);
    }

    @Bean
    public GetChatAiRunMetricByIdUseCase getChatAiRunMetricByIdUseCase(ChatAiRunMetricRepository repository) {
        return new GetChatAiRunMetricByIdUseCase(repository);
    }

    @Bean
    public ListChatAiRunMetricUseCase listChatAiRunMetricUseCase(ChatAiRunMetricRepository repository) {
        return new ListChatAiRunMetricUseCase(repository);
    }
}
