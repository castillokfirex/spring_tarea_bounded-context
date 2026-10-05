package com.example.tarea.infrastructure.treatmentgoalstatus.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentgoalstatus.usecase.DeleteTreatmentGoalStatusUseCase;
import com.example.tarea.application.treatmentgoalstatus.usecase.GetTreatmentGoalStatusByIdUseCase;
import com.example.tarea.application.treatmentgoalstatus.usecase.ListTreatmentGoalStatusUseCase;
import com.example.tarea.application.treatmentgoalstatus.usecase.RegisterTreatmentGoalStatusUseCase;
import com.example.tarea.application.treatmentgoalstatus.usecase.UpdateTreatmentGoalStatusUseCase;
import com.example.tarea.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;
import com.example.tarea.infrastructure.treatmentgoalstatus.adapters.out.persistence.mappers.TreatmentGoalStatusPersistenceMapper;
import com.example.tarea.infrastructure.treatmentgoalstatus.adapters.out.persistence.repositories.TreatmentGoalStatusJpaRepository;
import com.example.tarea.infrastructure.treatmentgoalstatus.adapters.out.persistence.repositories.TreatmentGoalStatusRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context treatmentgoalstatus: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class TreatmentGoalStatusBeansConfig {

    @Bean
    public TreatmentGoalStatusPersistenceMapper treatmentGoalStatusPersistenceMapper() {
        return new TreatmentGoalStatusPersistenceMapper();
    }

    @Bean
    public TreatmentGoalStatusRepository treatmentGoalStatusRepository(TreatmentGoalStatusJpaRepository jpaRepository, TreatmentGoalStatusPersistenceMapper mapper) {
        return new TreatmentGoalStatusRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterTreatmentGoalStatusUseCase registerTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterTreatmentGoalStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateTreatmentGoalStatusUseCase updateTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateTreatmentGoalStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteTreatmentGoalStatusUseCase deleteTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteTreatmentGoalStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public GetTreatmentGoalStatusByIdUseCase getTreatmentGoalStatusByIdUseCase(TreatmentGoalStatusRepository repository) {
        return new GetTreatmentGoalStatusByIdUseCase(repository);
    }

    @Bean
    public ListTreatmentGoalStatusUseCase listTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) {
        return new ListTreatmentGoalStatusUseCase(repository);
    }
}
