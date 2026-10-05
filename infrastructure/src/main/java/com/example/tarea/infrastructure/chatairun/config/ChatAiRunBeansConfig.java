package com.example.tarea.infrastructure.chatairun.config;

import com.example.tarea.application.chatairun.usecase.DeleteChatAiRunUseCase;
import com.example.tarea.application.chatairun.usecase.GetChatAiRunByIdUseCase;
import com.example.tarea.application.chatairun.usecase.ListChatAiRunUseCase;
import com.example.tarea.application.chatairun.usecase.RegisterChatAiRunUseCase;
import com.example.tarea.application.chatairun.usecase.UpdateChatAiRunUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatairun.port.repository.ChatAiRunRepository;
import com.example.tarea.infrastructure.chatairun.adapters.out.persistence.mappers.ChatAiRunPersistenceMapper;
import com.example.tarea.infrastructure.chatairun.adapters.out.persistence.repositories.ChatAiRunJpaRepository;
import com.example.tarea.infrastructure.chatairun.adapters.out.persistence.repositories.ChatAiRunRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context chatairun: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ChatAiRunBeansConfig {

    @Bean
    public ChatAiRunPersistenceMapper chatAiRunPersistenceMapper() {
        return new ChatAiRunPersistenceMapper();
    }

    @Bean
    public ChatAiRunRepository chatAiRunRepository(ChatAiRunJpaRepository jpaRepository, ChatAiRunPersistenceMapper mapper) {
        return new ChatAiRunRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterChatAiRunUseCase registerChatAiRunUseCase(ChatAiRunRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterChatAiRunUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateChatAiRunUseCase updateChatAiRunUseCase(ChatAiRunRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateChatAiRunUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteChatAiRunUseCase deleteChatAiRunUseCase(ChatAiRunRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteChatAiRunUseCase(repository, eventPublisher);
    }

    @Bean
    public GetChatAiRunByIdUseCase getChatAiRunByIdUseCase(ChatAiRunRepository repository) {
        return new GetChatAiRunByIdUseCase(repository);
    }

    @Bean
    public ListChatAiRunUseCase listChatAiRunUseCase(ChatAiRunRepository repository) {
        return new ListChatAiRunUseCase(repository);
    }
}
