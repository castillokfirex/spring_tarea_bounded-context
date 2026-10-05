package com.example.tarea.infrastructure.encounter.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encounter.usecase.DeleteEncounterUseCase;
import com.example.tarea.application.encounter.usecase.GetEncounterByIdUseCase;
import com.example.tarea.application.encounter.usecase.ListEncounterUseCase;
import com.example.tarea.application.encounter.usecase.RegisterEncounterUseCase;
import com.example.tarea.application.encounter.usecase.UpdateEncounterUseCase;
import com.example.tarea.domain.encounter.port.repository.EncounterRepository;
import com.example.tarea.infrastructure.encounter.adapters.out.persistence.mappers.EncounterPersistenceMapper;
import com.example.tarea.infrastructure.encounter.adapters.out.persistence.repositories.EncounterJpaRepository;
import com.example.tarea.infrastructure.encounter.adapters.out.persistence.repositories.EncounterRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context encounter: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class EncounterBeansConfig {

    @Bean
    public EncounterPersistenceMapper encounterPersistenceMapper() {
        return new EncounterPersistenceMapper();
    }

    @Bean
    public EncounterRepository encounterRepository(EncounterJpaRepository jpaRepository, EncounterPersistenceMapper mapper) {
        return new EncounterRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterEncounterUseCase registerEncounterUseCase(EncounterRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterEncounterUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateEncounterUseCase updateEncounterUseCase(EncounterRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateEncounterUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteEncounterUseCase deleteEncounterUseCase(EncounterRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteEncounterUseCase(repository, eventPublisher);
    }

    @Bean
    public GetEncounterByIdUseCase getEncounterByIdUseCase(EncounterRepository repository) {
        return new GetEncounterByIdUseCase(repository);
    }

    @Bean
    public ListEncounterUseCase listEncounterUseCase(EncounterRepository repository) {
        return new ListEncounterUseCase(repository);
    }
}
