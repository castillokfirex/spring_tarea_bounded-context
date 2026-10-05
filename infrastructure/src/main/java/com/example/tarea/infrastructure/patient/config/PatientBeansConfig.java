package com.example.tarea.infrastructure.patient.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.patient.usecase.DeletePatientUseCase;
import com.example.tarea.application.patient.usecase.GetPatientByIdUseCase;
import com.example.tarea.application.patient.usecase.ListPatientUseCase;
import com.example.tarea.application.patient.usecase.RegisterPatientUseCase;
import com.example.tarea.application.patient.usecase.UpdatePatientUseCase;
import com.example.tarea.domain.patient.port.repository.PatientRepository;
import com.example.tarea.infrastructure.patient.adapters.out.persistence.mappers.PatientPersistenceMapper;
import com.example.tarea.infrastructure.patient.adapters.out.persistence.repositories.PatientJpaRepository;
import com.example.tarea.infrastructure.patient.adapters.out.persistence.repositories.PatientRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context patient: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class PatientBeansConfig {

    @Bean
    public PatientPersistenceMapper patientPersistenceMapper() {
        return new PatientPersistenceMapper();
    }

    @Bean
    public PatientRepository patientRepository(PatientJpaRepository jpaRepository, PatientPersistenceMapper mapper) {
        return new PatientRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterPatientUseCase registerPatientUseCase(PatientRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterPatientUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdatePatientUseCase updatePatientUseCase(PatientRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdatePatientUseCase(repository, eventPublisher);
    }

    @Bean
    public DeletePatientUseCase deletePatientUseCase(PatientRepository repository, DomainEventPublisher eventPublisher) {
        return new DeletePatientUseCase(repository, eventPublisher);
    }

    @Bean
    public GetPatientByIdUseCase getPatientByIdUseCase(PatientRepository repository) {
        return new GetPatientByIdUseCase(repository);
    }

    @Bean
    public ListPatientUseCase listPatientUseCase(PatientRepository repository) {
        return new ListPatientUseCase(repository);
    }
}
