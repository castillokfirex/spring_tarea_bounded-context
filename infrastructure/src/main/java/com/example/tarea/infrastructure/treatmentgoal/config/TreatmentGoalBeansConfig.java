package com.example.tarea.infrastructure.treatmentgoal.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentgoal.usecase.DeleteTreatmentGoalUseCase;
import com.example.tarea.application.treatmentgoal.usecase.GetTreatmentGoalByIdUseCase;
import com.example.tarea.application.treatmentgoal.usecase.ListTreatmentGoalUseCase;
import com.example.tarea.application.treatmentgoal.usecase.RegisterTreatmentGoalUseCase;
import com.example.tarea.application.treatmentgoal.usecase.UpdateTreatmentGoalUseCase;
import com.example.tarea.domain.treatmentgoal.port.repository.TreatmentGoalRepository;
import com.example.tarea.infrastructure.treatmentgoal.adapters.out.persistence.mappers.TreatmentGoalPersistenceMapper;
import com.example.tarea.infrastructure.treatmentgoal.adapters.out.persistence.repositories.TreatmentGoalJpaRepository;
import com.example.tarea.infrastructure.treatmentgoal.adapters.out.persistence.repositories.TreatmentGoalRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context treatmentgoal: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class TreatmentGoalBeansConfig {

    @Bean
    public TreatmentGoalPersistenceMapper treatmentGoalPersistenceMapper() {
        return new TreatmentGoalPersistenceMapper();
    }

    @Bean
    public TreatmentGoalRepository treatmentGoalRepository(TreatmentGoalJpaRepository jpaRepository, TreatmentGoalPersistenceMapper mapper) {
        return new TreatmentGoalRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterTreatmentGoalUseCase registerTreatmentGoalUseCase(TreatmentGoalRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterTreatmentGoalUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateTreatmentGoalUseCase updateTreatmentGoalUseCase(TreatmentGoalRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateTreatmentGoalUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteTreatmentGoalUseCase deleteTreatmentGoalUseCase(TreatmentGoalRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteTreatmentGoalUseCase(repository, eventPublisher);
    }

    @Bean
    public GetTreatmentGoalByIdUseCase getTreatmentGoalByIdUseCase(TreatmentGoalRepository repository) {
        return new GetTreatmentGoalByIdUseCase(repository);
    }

    @Bean
    public ListTreatmentGoalUseCase listTreatmentGoalUseCase(TreatmentGoalRepository repository) {
        return new ListTreatmentGoalUseCase(repository);
    }
}
