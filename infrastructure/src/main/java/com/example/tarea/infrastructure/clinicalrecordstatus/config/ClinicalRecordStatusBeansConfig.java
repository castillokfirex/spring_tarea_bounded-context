package com.example.tarea.infrastructure.clinicalrecordstatus.config;

import com.example.tarea.application.clinicalrecordstatus.usecase.DeleteClinicalRecordStatusUseCase;
import com.example.tarea.application.clinicalrecordstatus.usecase.GetClinicalRecordStatusByIdUseCase;
import com.example.tarea.application.clinicalrecordstatus.usecase.ListClinicalRecordStatusUseCase;
import com.example.tarea.application.clinicalrecordstatus.usecase.RegisterClinicalRecordStatusUseCase;
import com.example.tarea.application.clinicalrecordstatus.usecase.UpdateClinicalRecordStatusUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;
import com.example.tarea.infrastructure.clinicalrecordstatus.adapters.out.persistence.mappers.ClinicalRecordStatusPersistenceMapper;
import com.example.tarea.infrastructure.clinicalrecordstatus.adapters.out.persistence.repositories.ClinicalRecordStatusJpaRepository;
import com.example.tarea.infrastructure.clinicalrecordstatus.adapters.out.persistence.repositories.ClinicalRecordStatusRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context clinicalrecordstatus: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ClinicalRecordStatusBeansConfig {

    @Bean
    public ClinicalRecordStatusPersistenceMapper clinicalRecordStatusPersistenceMapper() {
        return new ClinicalRecordStatusPersistenceMapper();
    }

    @Bean
    public ClinicalRecordStatusRepository clinicalRecordStatusRepository(ClinicalRecordStatusJpaRepository jpaRepository, ClinicalRecordStatusPersistenceMapper mapper) {
        return new ClinicalRecordStatusRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterClinicalRecordStatusUseCase registerClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterClinicalRecordStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateClinicalRecordStatusUseCase updateClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateClinicalRecordStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteClinicalRecordStatusUseCase deleteClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteClinicalRecordStatusUseCase(repository, eventPublisher);
    }

    @Bean
    public GetClinicalRecordStatusByIdUseCase getClinicalRecordStatusByIdUseCase(ClinicalRecordStatusRepository repository) {
        return new GetClinicalRecordStatusByIdUseCase(repository);
    }

    @Bean
    public ListClinicalRecordStatusUseCase listClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) {
        return new ListClinicalRecordStatusUseCase(repository);
    }
}
