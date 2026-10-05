package com.example.tarea.infrastructure.aimodel.config;

import com.example.tarea.application.aimodel.usecase.DeleteAiModelUseCase;
import com.example.tarea.application.aimodel.usecase.GetAiModelByIdUseCase;
import com.example.tarea.application.aimodel.usecase.ListAiModelUseCase;
import com.example.tarea.application.aimodel.usecase.RegisterAiModelUseCase;
import com.example.tarea.application.aimodel.usecase.UpdateAiModelUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.aimodel.port.repository.AiModelRepository;
import com.example.tarea.infrastructure.aimodel.adapters.out.persistence.mappers.AiModelPersistenceMapper;
import com.example.tarea.infrastructure.aimodel.adapters.out.persistence.repositories.AiModelJpaRepository;
import com.example.tarea.infrastructure.aimodel.adapters.out.persistence.repositories.AiModelRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context aimodel: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class AiModelBeansConfig {

    @Bean
    public AiModelPersistenceMapper aiModelPersistenceMapper() {
        return new AiModelPersistenceMapper();
    }

    @Bean
    public AiModelRepository aiModelRepository(AiModelJpaRepository jpaRepository, AiModelPersistenceMapper mapper) {
        return new AiModelRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterAiModelUseCase registerAiModelUseCase(AiModelRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterAiModelUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateAiModelUseCase updateAiModelUseCase(AiModelRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateAiModelUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteAiModelUseCase deleteAiModelUseCase(AiModelRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteAiModelUseCase(repository, eventPublisher);
    }

    @Bean
    public GetAiModelByIdUseCase getAiModelByIdUseCase(AiModelRepository repository) {
        return new GetAiModelByIdUseCase(repository);
    }

    @Bean
    public ListAiModelUseCase listAiModelUseCase(AiModelRepository repository) {
        return new ListAiModelUseCase(repository);
    }
}
