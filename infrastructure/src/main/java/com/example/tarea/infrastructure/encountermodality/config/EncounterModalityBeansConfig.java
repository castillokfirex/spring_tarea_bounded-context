package com.example.tarea.infrastructure.encountermodality.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encountermodality.usecase.DeleteEncounterModalityUseCase;
import com.example.tarea.application.encountermodality.usecase.GetEncounterModalityByIdUseCase;
import com.example.tarea.application.encountermodality.usecase.ListEncounterModalityUseCase;
import com.example.tarea.application.encountermodality.usecase.RegisterEncounterModalityUseCase;
import com.example.tarea.application.encountermodality.usecase.UpdateEncounterModalityUseCase;
import com.example.tarea.domain.encountermodality.port.repository.EncounterModalityRepository;
import com.example.tarea.infrastructure.encountermodality.adapters.out.persistence.mappers.EncounterModalityPersistenceMapper;
import com.example.tarea.infrastructure.encountermodality.adapters.out.persistence.repositories.EncounterModalityJpaRepository;
import com.example.tarea.infrastructure.encountermodality.adapters.out.persistence.repositories.EncounterModalityRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context encountermodality: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class EncounterModalityBeansConfig {

    @Bean
    public EncounterModalityPersistenceMapper encounterModalityPersistenceMapper() {
        return new EncounterModalityPersistenceMapper();
    }

    @Bean
    public EncounterModalityRepository encounterModalityRepository(EncounterModalityJpaRepository jpaRepository, EncounterModalityPersistenceMapper mapper) {
        return new EncounterModalityRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterEncounterModalityUseCase registerEncounterModalityUseCase(EncounterModalityRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterEncounterModalityUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateEncounterModalityUseCase updateEncounterModalityUseCase(EncounterModalityRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateEncounterModalityUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteEncounterModalityUseCase deleteEncounterModalityUseCase(EncounterModalityRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteEncounterModalityUseCase(repository, eventPublisher);
    }

    @Bean
    public GetEncounterModalityByIdUseCase getEncounterModalityByIdUseCase(EncounterModalityRepository repository) {
        return new GetEncounterModalityByIdUseCase(repository);
    }

    @Bean
    public ListEncounterModalityUseCase listEncounterModalityUseCase(EncounterModalityRepository repository) {
        return new ListEncounterModalityUseCase(repository);
    }
}
