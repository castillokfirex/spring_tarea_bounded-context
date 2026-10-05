package com.example.tarea.infrastructure.chatairunerror.config;

import com.example.tarea.application.chatairunerror.usecase.DeleteChatAiRunErrorUseCase;
import com.example.tarea.application.chatairunerror.usecase.GetChatAiRunErrorByIdUseCase;
import com.example.tarea.application.chatairunerror.usecase.ListChatAiRunErrorUseCase;
import com.example.tarea.application.chatairunerror.usecase.RegisterChatAiRunErrorUseCase;
import com.example.tarea.application.chatairunerror.usecase.UpdateChatAiRunErrorUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;
import com.example.tarea.infrastructure.chatairunerror.adapters.out.persistence.mappers.ChatAiRunErrorPersistenceMapper;
import com.example.tarea.infrastructure.chatairunerror.adapters.out.persistence.repositories.ChatAiRunErrorJpaRepository;
import com.example.tarea.infrastructure.chatairunerror.adapters.out.persistence.repositories.ChatAiRunErrorRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context chatairunerror: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ChatAiRunErrorBeansConfig {

    @Bean
    public ChatAiRunErrorPersistenceMapper chatAiRunErrorPersistenceMapper() {
        return new ChatAiRunErrorPersistenceMapper();
    }

    @Bean
    public ChatAiRunErrorRepository chatAiRunErrorRepository(ChatAiRunErrorJpaRepository jpaRepository, ChatAiRunErrorPersistenceMapper mapper) {
        return new ChatAiRunErrorRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterChatAiRunErrorUseCase registerChatAiRunErrorUseCase(ChatAiRunErrorRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterChatAiRunErrorUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateChatAiRunErrorUseCase updateChatAiRunErrorUseCase(ChatAiRunErrorRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateChatAiRunErrorUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteChatAiRunErrorUseCase deleteChatAiRunErrorUseCase(ChatAiRunErrorRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteChatAiRunErrorUseCase(repository, eventPublisher);
    }

    @Bean
    public GetChatAiRunErrorByIdUseCase getChatAiRunErrorByIdUseCase(ChatAiRunErrorRepository repository) {
        return new GetChatAiRunErrorByIdUseCase(repository);
    }

    @Bean
    public ListChatAiRunErrorUseCase listChatAiRunErrorUseCase(ChatAiRunErrorRepository repository) {
        return new ListChatAiRunErrorUseCase(repository);
    }
}
