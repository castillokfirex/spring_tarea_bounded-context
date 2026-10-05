package com.example.tarea.infrastructure.encountertype.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encountertype.usecase.DeleteEncounterTypeUseCase;
import com.example.tarea.application.encountertype.usecase.GetEncounterTypeByIdUseCase;
import com.example.tarea.application.encountertype.usecase.ListEncounterTypeUseCase;
import com.example.tarea.application.encountertype.usecase.RegisterEncounterTypeUseCase;
import com.example.tarea.application.encountertype.usecase.UpdateEncounterTypeUseCase;
import com.example.tarea.domain.encountertype.port.repository.EncounterTypeRepository;
import com.example.tarea.infrastructure.encountertype.adapters.out.persistence.mappers.EncounterTypePersistenceMapper;
import com.example.tarea.infrastructure.encountertype.adapters.out.persistence.repositories.EncounterTypeJpaRepository;
import com.example.tarea.infrastructure.encountertype.adapters.out.persistence.repositories.EncounterTypeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context encountertype: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class EncounterTypeBeansConfig {

    @Bean
    public EncounterTypePersistenceMapper encounterTypePersistenceMapper() {
        return new EncounterTypePersistenceMapper();
    }

    @Bean
    public EncounterTypeRepository encounterTypeRepository(EncounterTypeJpaRepository jpaRepository, EncounterTypePersistenceMapper mapper) {
        return new EncounterTypeRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterEncounterTypeUseCase registerEncounterTypeUseCase(EncounterTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterEncounterTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateEncounterTypeUseCase updateEncounterTypeUseCase(EncounterTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateEncounterTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteEncounterTypeUseCase deleteEncounterTypeUseCase(EncounterTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteEncounterTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public GetEncounterTypeByIdUseCase getEncounterTypeByIdUseCase(EncounterTypeRepository repository) {
        return new GetEncounterTypeByIdUseCase(repository);
    }

    @Bean
    public ListEncounterTypeUseCase listEncounterTypeUseCase(EncounterTypeRepository repository) {
        return new ListEncounterTypeUseCase(repository);
    }
}
