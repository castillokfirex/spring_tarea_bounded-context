package com.example.tarea.infrastructure.airunstatus.config;

import com.example.tarea.application.airunstatus.usecase.DeleteAiRunStatusUseCase;
import com.example.tarea.application.airunstatus.usecase.GetAiRunStatusByIdUseCase;
import com.example.tarea.application.airunstatus.usecase.ListAiRunStatusUseCase;
import com.example.tarea.application.airunstatus.usecase.RegisterAiRunStatusUseCase;
import com.example.tarea.application.airunstatus.usecase.UpdateAiRunStatusUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.airunstatus.port.repository.AiRunStatusRepository;
import com.example.tarea.infrastructure.airunstatus.adapters.out.persistence.mappers.AiRunStatusPersistenceMapper;
import com.example.tarea.infrastructure.airunstatus.adapters.out.persistence.repositories.AiRunStatusJpaRepository;
import com.example.tarea.infrastructure.airunstatus.adapters.out.persistence.repositories.AiRunStatusRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context airunstatus: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class AiRunStatusBeansConfig {

    @Bean
    public AiRunStatusPersistenceMapper aiRunStatusPersistenceMapper() {
        return new AiRunStatusPersistenceMapper();
    }

    @Bean
    public AiRunStatusRepository aiRunStatusRepository(AiRunStatusJpaRepository jpaRepository, AiRunStatusPersistenceMapper mapper) {
        return new AiRunStatusRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterAiRunStatusUseCase registerAiRunStatusUseCase(AiRunStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterAiRunStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateAiRunStatusUseCase updateAiRunStatusUseCase(AiRunStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateAiRunStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteAiRunStatusUseCase deleteAiRunStatusUseCase(AiRunStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteAiRunStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public GetAiRunStatusByIdUseCase getAiRunStatusByIdUseCase(AiRunStatusRepository repository) {
        return new GetAiRunStatusByIdUseCase(repository);
    }

    @Bean
    public ListAiRunStatusUseCase listAiRunStatusUseCase(AiRunStatusRepository repository) {
        return new ListAiRunStatusUseCase(repository);
    }
}
