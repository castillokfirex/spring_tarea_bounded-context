package com.example.tarea.infrastructure.patientcontact.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.patientcontact.usecase.DeletePatientContactUseCase;
import com.example.tarea.application.patientcontact.usecase.GetPatientContactByIdUseCase;
import com.example.tarea.application.patientcontact.usecase.ListPatientContactUseCase;
import com.example.tarea.application.patientcontact.usecase.RegisterPatientContactUseCase;
import com.example.tarea.application.patientcontact.usecase.UpdatePatientContactUseCase;
import com.example.tarea.domain.patientcontact.port.repository.PatientContactRepository;
import com.example.tarea.infrastructure.patientcontact.adapters.out.persistence.mappers.PatientContactPersistenceMapper;
import com.example.tarea.infrastructure.patientcontact.adapters.out.persistence.repositories.PatientContactJpaRepository;
import com.example.tarea.infrastructure.patientcontact.adapters.out.persistence.repositories.PatientContactRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context patientcontact: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class PatientContactBeansConfig {

    @Bean
    public PatientContactPersistenceMapper patientContactPersistenceMapper() {
        return new PatientContactPersistenceMapper();
    }

    @Bean
    public PatientContactRepository patientContactRepository(PatientContactJpaRepository jpaRepository, PatientContactPersistenceMapper mapper) {
        return new PatientContactRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterPatientContactUseCase registerPatientContactUseCase(PatientContactRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterPatientContactUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdatePatientContactUseCase updatePatientContactUseCase(PatientContactRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdatePatientContactUseCase(repository, eventPublisher);
    }

    @Bean
    public DeletePatientContactUseCase deletePatientContactUseCase(PatientContactRepository repository, DomainEventPublisher eventPublisher) {
        return new DeletePatientContactUseCase(repository, eventPublisher);
    }

    @Bean
    public GetPatientContactByIdUseCase getPatientContactByIdUseCase(PatientContactRepository repository) {
        return new GetPatientContactByIdUseCase(repository);
    }

    @Bean
    public ListPatientContactUseCase listPatientContactUseCase(PatientContactRepository repository) {
        return new ListPatientContactUseCase(repository);
    }
}
