package com.example.tarea.infrastructure.treatmentstatus.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentstatus.usecase.DeleteTreatmentStatusUseCase;
import com.example.tarea.application.treatmentstatus.usecase.GetTreatmentStatusByIdUseCase;
import com.example.tarea.application.treatmentstatus.usecase.ListTreatmentStatusUseCase;
import com.example.tarea.application.treatmentstatus.usecase.RegisterTreatmentStatusUseCase;
import com.example.tarea.application.treatmentstatus.usecase.UpdateTreatmentStatusUseCase;
import com.example.tarea.domain.treatmentstatus.port.repository.TreatmentStatusRepository;
import com.example.tarea.infrastructure.treatmentstatus.adapters.out.persistence.mappers.TreatmentStatusPersistenceMapper;
import com.example.tarea.infrastructure.treatmentstatus.adapters.out.persistence.repositories.TreatmentStatusJpaRepository;
import com.example.tarea.infrastructure.treatmentstatus.adapters.out.persistence.repositories.TreatmentStatusRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context treatmentstatus: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class TreatmentStatusBeansConfig {

    @Bean
    public TreatmentStatusPersistenceMapper treatmentStatusPersistenceMapper() {
        return new TreatmentStatusPersistenceMapper();
    }

    @Bean
    public TreatmentStatusRepository treatmentStatusRepository(TreatmentStatusJpaRepository jpaRepository, TreatmentStatusPersistenceMapper mapper) {
        return new TreatmentStatusRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterTreatmentStatusUseCase registerTreatmentStatusUseCase(TreatmentStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterTreatmentStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateTreatmentStatusUseCase updateTreatmentStatusUseCase(TreatmentStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateTreatmentStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteTreatmentStatusUseCase deleteTreatmentStatusUseCase(TreatmentStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteTreatmentStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public GetTreatmentStatusByIdUseCase getTreatmentStatusByIdUseCase(TreatmentStatusRepository repository) {
        return new GetTreatmentStatusByIdUseCase(repository);
    }

    @Bean
    public ListTreatmentStatusUseCase listTreatmentStatusUseCase(TreatmentStatusRepository repository) {
        return new ListTreatmentStatusUseCase(repository);
    }
}
