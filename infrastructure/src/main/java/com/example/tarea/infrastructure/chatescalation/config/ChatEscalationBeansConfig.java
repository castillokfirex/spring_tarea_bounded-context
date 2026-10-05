package com.example.tarea.infrastructure.chatescalation.config;

import com.example.tarea.application.chatescalation.usecase.DeleteChatEscalationUseCase;
import com.example.tarea.application.chatescalation.usecase.GetChatEscalationByIdUseCase;
import com.example.tarea.application.chatescalation.usecase.ListChatEscalationUseCase;
import com.example.tarea.application.chatescalation.usecase.RegisterChatEscalationUseCase;
import com.example.tarea.application.chatescalation.usecase.UpdateChatEscalationUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.example.tarea.infrastructure.chatescalation.adapters.out.persistence.mappers.ChatEscalationPersistenceMapper;
import com.example.tarea.infrastructure.chatescalation.adapters.out.persistence.repositories.ChatEscalationJpaRepository;
import com.example.tarea.infrastructure.chatescalation.adapters.out.persistence.repositories.ChatEscalationRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context chatescalation: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ChatEscalationBeansConfig {

    @Bean
    public ChatEscalationPersistenceMapper chatEscalationPersistenceMapper() {
        return new ChatEscalationPersistenceMapper();
    }

    @Bean
    public ChatEscalationRepository chatEscalationRepository(ChatEscalationJpaRepository jpaRepository, ChatEscalationPersistenceMapper mapper) {
        return new ChatEscalationRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterChatEscalationUseCase registerChatEscalationUseCase(ChatEscalationRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterChatEscalationUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateChatEscalationUseCase updateChatEscalationUseCase(ChatEscalationRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateChatEscalationUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteChatEscalationUseCase deleteChatEscalationUseCase(ChatEscalationRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteChatEscalationUseCase(repository, eventPublisher);
    }

    @Bean
    public GetChatEscalationByIdUseCase getChatEscalationByIdUseCase(ChatEscalationRepository repository) {
        return new GetChatEscalationByIdUseCase(repository);
    }

    @Bean
    public ListChatEscalationUseCase listChatEscalationUseCase(ChatEscalationRepository repository) {
        return new ListChatEscalationUseCase(repository);
    }
}
