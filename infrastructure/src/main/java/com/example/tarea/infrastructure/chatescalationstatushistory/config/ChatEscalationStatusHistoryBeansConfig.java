package com.example.tarea.infrastructure.chatescalationstatushistory.config;

import com.example.tarea.application.chatescalationstatushistory.usecase.DeleteChatEscalationStatusHistoryUseCase;
import com.example.tarea.application.chatescalationstatushistory.usecase.GetChatEscalationStatusHistoryByIdUseCase;
import com.example.tarea.application.chatescalationstatushistory.usecase.ListChatEscalationStatusHistoryUseCase;
import com.example.tarea.application.chatescalationstatushistory.usecase.RegisterChatEscalationStatusHistoryUseCase;
import com.example.tarea.application.chatescalationstatushistory.usecase.UpdateChatEscalationStatusHistoryUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;
import com.example.tarea.infrastructure.chatescalationstatushistory.adapters.out.persistence.mappers.ChatEscalationStatusHistoryPersistenceMapper;
import com.example.tarea.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories.ChatEscalationStatusHistoryJpaRepository;
import com.example.tarea.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories.ChatEscalationStatusHistoryRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context chatescalationstatushistory: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ChatEscalationStatusHistoryBeansConfig {

    @Bean
    public ChatEscalationStatusHistoryPersistenceMapper chatEscalationStatusHistoryPersistenceMapper() {
        return new ChatEscalationStatusHistoryPersistenceMapper();
    }

    @Bean
    public ChatEscalationStatusHistoryRepository chatEscalationStatusHistoryRepository(ChatEscalationStatusHistoryJpaRepository jpaRepository, ChatEscalationStatusHistoryPersistenceMapper mapper) {
        return new ChatEscalationStatusHistoryRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterChatEscalationStatusHistoryUseCase registerChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterChatEscalationStatusHistoryUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateChatEscalationStatusHistoryUseCase updateChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateChatEscalationStatusHistoryUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteChatEscalationStatusHistoryUseCase deleteChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteChatEscalationStatusHistoryUseCase(repository, eventPublisher);
    }

    @Bean
    public GetChatEscalationStatusHistoryByIdUseCase getChatEscalationStatusHistoryByIdUseCase(ChatEscalationStatusHistoryRepository repository) {
        return new GetChatEscalationStatusHistoryByIdUseCase(repository);
    }

    @Bean
    public ListChatEscalationStatusHistoryUseCase listChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) {
        return new ListChatEscalationStatusHistoryUseCase(repository);
    }
}
