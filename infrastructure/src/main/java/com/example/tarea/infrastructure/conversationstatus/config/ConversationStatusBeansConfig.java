package com.example.tarea.infrastructure.conversationstatus.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.conversationstatus.usecase.DeleteConversationStatusUseCase;
import com.example.tarea.application.conversationstatus.usecase.GetConversationStatusByIdUseCase;
import com.example.tarea.application.conversationstatus.usecase.ListConversationStatusUseCase;
import com.example.tarea.application.conversationstatus.usecase.RegisterConversationStatusUseCase;
import com.example.tarea.application.conversationstatus.usecase.UpdateConversationStatusUseCase;
import com.example.tarea.domain.conversationstatus.port.repository.ConversationStatusRepository;
import com.example.tarea.infrastructure.conversationstatus.adapters.out.persistence.mappers.ConversationStatusPersistenceMapper;
import com.example.tarea.infrastructure.conversationstatus.adapters.out.persistence.repositories.ConversationStatusJpaRepository;
import com.example.tarea.infrastructure.conversationstatus.adapters.out.persistence.repositories.ConversationStatusRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context conversationstatus: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ConversationStatusBeansConfig {

    @Bean
    public ConversationStatusPersistenceMapper conversationStatusPersistenceMapper() {
        return new ConversationStatusPersistenceMapper();
    }

    @Bean
    public ConversationStatusRepository conversationStatusRepository(ConversationStatusJpaRepository jpaRepository, ConversationStatusPersistenceMapper mapper) {
        return new ConversationStatusRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterConversationStatusUseCase registerConversationStatusUseCase(ConversationStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterConversationStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateConversationStatusUseCase updateConversationStatusUseCase(ConversationStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateConversationStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteConversationStatusUseCase deleteConversationStatusUseCase(ConversationStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteConversationStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public GetConversationStatusByIdUseCase getConversationStatusByIdUseCase(ConversationStatusRepository repository) {
        return new GetConversationStatusByIdUseCase(repository);
    }

    @Bean
    public ListConversationStatusUseCase listConversationStatusUseCase(ConversationStatusRepository repository) {
        return new ListConversationStatusUseCase(repository);
    }
}
