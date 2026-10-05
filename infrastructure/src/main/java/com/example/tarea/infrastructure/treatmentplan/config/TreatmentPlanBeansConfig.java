package com.example.tarea.infrastructure.treatmentplan.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.treatmentplan.usecase.DeleteTreatmentPlanUseCase;
import com.example.tarea.application.treatmentplan.usecase.GetTreatmentPlanByIdUseCase;
import com.example.tarea.application.treatmentplan.usecase.ListTreatmentPlanUseCase;
import com.example.tarea.application.treatmentplan.usecase.RegisterTreatmentPlanUseCase;
import com.example.tarea.application.treatmentplan.usecase.UpdateTreatmentPlanUseCase;
import com.example.tarea.domain.treatmentplan.port.repository.TreatmentPlanRepository;
import com.example.tarea.infrastructure.treatmentplan.adapters.out.persistence.mappers.TreatmentPlanPersistenceMapper;
import com.example.tarea.infrastructure.treatmentplan.adapters.out.persistence.repositories.TreatmentPlanJpaRepository;
import com.example.tarea.infrastructure.treatmentplan.adapters.out.persistence.repositories.TreatmentPlanRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context treatmentplan: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class TreatmentPlanBeansConfig {

    @Bean
    public TreatmentPlanPersistenceMapper treatmentPlanPersistenceMapper() {
        return new TreatmentPlanPersistenceMapper();
    }

    @Bean
    public TreatmentPlanRepository treatmentPlanRepository(TreatmentPlanJpaRepository jpaRepository, TreatmentPlanPersistenceMapper mapper) {
        return new TreatmentPlanRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterTreatmentPlanUseCase registerTreatmentPlanUseCase(TreatmentPlanRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterTreatmentPlanUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateTreatmentPlanUseCase updateTreatmentPlanUseCase(TreatmentPlanRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateTreatmentPlanUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteTreatmentPlanUseCase deleteTreatmentPlanUseCase(TreatmentPlanRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteTreatmentPlanUseCase(repository, eventPublisher);
    }

    @Bean
    public GetTreatmentPlanByIdUseCase getTreatmentPlanByIdUseCase(TreatmentPlanRepository repository) {
        return new GetTreatmentPlanByIdUseCase(repository);
    }

    @Bean
    public ListTreatmentPlanUseCase listTreatmentPlanUseCase(TreatmentPlanRepository repository) {
        return new ListTreatmentPlanUseCase(repository);
    }
}
