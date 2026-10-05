package com.example.tarea.infrastructure.priority.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.priority.usecase.DeletePriorityUseCase;
import com.example.tarea.application.priority.usecase.GetPriorityByIdUseCase;
import com.example.tarea.application.priority.usecase.ListPriorityUseCase;
import com.example.tarea.application.priority.usecase.RegisterPriorityUseCase;
import com.example.tarea.application.priority.usecase.UpdatePriorityUseCase;
import com.example.tarea.domain.priority.port.repository.PriorityRepository;
import com.example.tarea.infrastructure.priority.adapters.out.persistence.mappers.PriorityPersistenceMapper;
import com.example.tarea.infrastructure.priority.adapters.out.persistence.repositories.PriorityJpaRepository;
import com.example.tarea.infrastructure.priority.adapters.out.persistence.repositories.PriorityRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context priority: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class PriorityBeansConfig {

    @Bean
    public PriorityPersistenceMapper priorityPersistenceMapper() {
        return new PriorityPersistenceMapper();
    }

    @Bean
    public PriorityRepository priorityRepository(PriorityJpaRepository jpaRepository, PriorityPersistenceMapper mapper) {
        return new PriorityRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterPriorityUseCase registerPriorityUseCase(PriorityRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterPriorityUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdatePriorityUseCase updatePriorityUseCase(PriorityRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdatePriorityUseCase(repository, eventPublisher);
    }

    @Bean
    public DeletePriorityUseCase deletePriorityUseCase(PriorityRepository repository, DomainEventPublisher eventPublisher) {
        return new DeletePriorityUseCase(repository, eventPublisher);
    }

    @Bean
    public GetPriorityByIdUseCase getPriorityByIdUseCase(PriorityRepository repository) {
        return new GetPriorityByIdUseCase(repository);
    }

    @Bean
    public ListPriorityUseCase listPriorityUseCase(PriorityRepository repository) {
        return new ListPriorityUseCase(repository);
    }
}
