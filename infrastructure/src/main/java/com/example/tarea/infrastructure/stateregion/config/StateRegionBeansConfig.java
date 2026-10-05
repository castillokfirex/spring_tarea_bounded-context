package com.example.tarea.infrastructure.stateregion.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.stateregion.usecase.DeleteStateRegionUseCase;
import com.example.tarea.application.stateregion.usecase.GetStateRegionByIdUseCase;
import com.example.tarea.application.stateregion.usecase.ListStateRegionUseCase;
import com.example.tarea.application.stateregion.usecase.RegisterStateRegionUseCase;
import com.example.tarea.application.stateregion.usecase.UpdateStateRegionUseCase;
import com.example.tarea.domain.stateregion.port.repository.StateRegionRepository;
import com.example.tarea.infrastructure.stateregion.adapters.out.persistence.mappers.StateRegionPersistenceMapper;
import com.example.tarea.infrastructure.stateregion.adapters.out.persistence.repositories.StateRegionJpaRepository;
import com.example.tarea.infrastructure.stateregion.adapters.out.persistence.repositories.StateRegionRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context stateregion: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class StateRegionBeansConfig {

    @Bean
    public StateRegionPersistenceMapper stateRegionPersistenceMapper() {
        return new StateRegionPersistenceMapper();
    }

    @Bean
    public StateRegionRepository stateRegionRepository(StateRegionJpaRepository jpaRepository, StateRegionPersistenceMapper mapper) {
        return new StateRegionRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterStateRegionUseCase registerStateRegionUseCase(StateRegionRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterStateRegionUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateStateRegionUseCase updateStateRegionUseCase(StateRegionRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateStateRegionUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteStateRegionUseCase deleteStateRegionUseCase(StateRegionRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteStateRegionUseCase(repository, eventPublisher);
    }

    @Bean
    public GetStateRegionByIdUseCase getStateRegionByIdUseCase(StateRegionRepository repository) {
        return new GetStateRegionByIdUseCase(repository);
    }

    @Bean
    public ListStateRegionUseCase listStateRegionUseCase(StateRegionRepository repository) {
        return new ListStateRegionUseCase(repository);
    }
}
