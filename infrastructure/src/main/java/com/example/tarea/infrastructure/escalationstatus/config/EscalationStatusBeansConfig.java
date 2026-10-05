package com.example.tarea.infrastructure.escalationstatus.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.escalationstatus.usecase.DeleteEscalationStatusUseCase;
import com.example.tarea.application.escalationstatus.usecase.GetEscalationStatusByIdUseCase;
import com.example.tarea.application.escalationstatus.usecase.ListEscalationStatusUseCase;
import com.example.tarea.application.escalationstatus.usecase.RegisterEscalationStatusUseCase;
import com.example.tarea.application.escalationstatus.usecase.UpdateEscalationStatusUseCase;
import com.example.tarea.domain.escalationstatus.port.repository.EscalationStatusRepository;
import com.example.tarea.infrastructure.escalationstatus.adapters.out.persistence.mappers.EscalationStatusPersistenceMapper;
import com.example.tarea.infrastructure.escalationstatus.adapters.out.persistence.repositories.EscalationStatusJpaRepository;
import com.example.tarea.infrastructure.escalationstatus.adapters.out.persistence.repositories.EscalationStatusRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context escalationstatus: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class EscalationStatusBeansConfig {

    @Bean
    public EscalationStatusPersistenceMapper escalationStatusPersistenceMapper() {
        return new EscalationStatusPersistenceMapper();
    }

    @Bean
    public EscalationStatusRepository escalationStatusRepository(EscalationStatusJpaRepository jpaRepository, EscalationStatusPersistenceMapper mapper) {
        return new EscalationStatusRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterEscalationStatusUseCase registerEscalationStatusUseCase(EscalationStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterEscalationStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateEscalationStatusUseCase updateEscalationStatusUseCase(EscalationStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateEscalationStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteEscalationStatusUseCase deleteEscalationStatusUseCase(EscalationStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteEscalationStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public GetEscalationStatusByIdUseCase getEscalationStatusByIdUseCase(EscalationStatusRepository repository) {
        return new GetEscalationStatusByIdUseCase(repository);
    }

    @Bean
    public ListEscalationStatusUseCase listEscalationStatusUseCase(EscalationStatusRepository repository) {
        return new ListEscalationStatusUseCase(repository);
    }
}
