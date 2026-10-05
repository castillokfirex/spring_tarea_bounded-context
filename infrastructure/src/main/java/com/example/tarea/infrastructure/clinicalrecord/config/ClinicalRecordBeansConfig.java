package com.example.tarea.infrastructure.clinicalrecord.config;

import com.example.tarea.application.clinicalrecord.usecase.DeleteClinicalRecordUseCase;
import com.example.tarea.application.clinicalrecord.usecase.GetClinicalRecordByIdUseCase;
import com.example.tarea.application.clinicalrecord.usecase.ListClinicalRecordUseCase;
import com.example.tarea.application.clinicalrecord.usecase.RegisterClinicalRecordUseCase;
import com.example.tarea.application.clinicalrecord.usecase.UpdateClinicalRecordUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.clinicalrecord.port.repository.ClinicalRecordRepository;
import com.example.tarea.infrastructure.clinicalrecord.adapters.out.persistence.mappers.ClinicalRecordPersistenceMapper;
import com.example.tarea.infrastructure.clinicalrecord.adapters.out.persistence.repositories.ClinicalRecordJpaRepository;
import com.example.tarea.infrastructure.clinicalrecord.adapters.out.persistence.repositories.ClinicalRecordRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context clinicalrecord: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ClinicalRecordBeansConfig {

    @Bean
    public ClinicalRecordPersistenceMapper clinicalRecordPersistenceMapper() {
        return new ClinicalRecordPersistenceMapper();
    }

    @Bean
    public ClinicalRecordRepository clinicalRecordRepository(ClinicalRecordJpaRepository jpaRepository, ClinicalRecordPersistenceMapper mapper) {
        return new ClinicalRecordRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterClinicalRecordUseCase registerClinicalRecordUseCase(ClinicalRecordRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterClinicalRecordUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateClinicalRecordUseCase updateClinicalRecordUseCase(ClinicalRecordRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateClinicalRecordUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteClinicalRecordUseCase deleteClinicalRecordUseCase(ClinicalRecordRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteClinicalRecordUseCase(repository, eventPublisher);
    }

    @Bean
    public GetClinicalRecordByIdUseCase getClinicalRecordByIdUseCase(ClinicalRecordRepository repository) {
        return new GetClinicalRecordByIdUseCase(repository);
    }

    @Bean
    public ListClinicalRecordUseCase listClinicalRecordUseCase(ClinicalRecordRepository repository) {
        return new ListClinicalRecordUseCase(repository);
    }
}
