package com.example.tarea.infrastructure.encounterstatus.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.encounterstatus.usecase.DeleteEncounterStatusUseCase;
import com.example.tarea.application.encounterstatus.usecase.GetEncounterStatusByIdUseCase;
import com.example.tarea.application.encounterstatus.usecase.ListEncounterStatusUseCase;
import com.example.tarea.application.encounterstatus.usecase.RegisterEncounterStatusUseCase;
import com.example.tarea.application.encounterstatus.usecase.UpdateEncounterStatusUseCase;
import com.example.tarea.domain.encounterstatus.port.repository.EncounterStatusRepository;
import com.example.tarea.infrastructure.encounterstatus.adapters.out.persistence.mappers.EncounterStatusPersistenceMapper;
import com.example.tarea.infrastructure.encounterstatus.adapters.out.persistence.repositories.EncounterStatusJpaRepository;
import com.example.tarea.infrastructure.encounterstatus.adapters.out.persistence.repositories.EncounterStatusRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context encounterstatus: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class EncounterStatusBeansConfig {

    @Bean
    public EncounterStatusPersistenceMapper encounterStatusPersistenceMapper() {
        return new EncounterStatusPersistenceMapper();
    }

    @Bean
    public EncounterStatusRepository encounterStatusRepository(EncounterStatusJpaRepository jpaRepository, EncounterStatusPersistenceMapper mapper) {
        return new EncounterStatusRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterEncounterStatusUseCase registerEncounterStatusUseCase(EncounterStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterEncounterStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateEncounterStatusUseCase updateEncounterStatusUseCase(EncounterStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateEncounterStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteEncounterStatusUseCase deleteEncounterStatusUseCase(EncounterStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteEncounterStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public GetEncounterStatusByIdUseCase getEncounterStatusByIdUseCase(EncounterStatusRepository repository) {
        return new GetEncounterStatusByIdUseCase(repository);
    }

    @Bean
    public ListEncounterStatusUseCase listEncounterStatusUseCase(EncounterStatusRepository repository) {
        return new ListEncounterStatusUseCase(repository);
    }
}
