package com.example.tarea.infrastructure.patientallergy.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.patientallergy.usecase.DeletePatientAllergyUseCase;
import com.example.tarea.application.patientallergy.usecase.GetPatientAllergyByIdUseCase;
import com.example.tarea.application.patientallergy.usecase.ListPatientAllergyUseCase;
import com.example.tarea.application.patientallergy.usecase.RegisterPatientAllergyUseCase;
import com.example.tarea.application.patientallergy.usecase.UpdatePatientAllergyUseCase;
import com.example.tarea.domain.patientallergy.port.repository.PatientAllergyRepository;
import com.example.tarea.infrastructure.patientallergy.adapters.out.persistence.mappers.PatientAllergyPersistenceMapper;
import com.example.tarea.infrastructure.patientallergy.adapters.out.persistence.repositories.PatientAllergyJpaRepository;
import com.example.tarea.infrastructure.patientallergy.adapters.out.persistence.repositories.PatientAllergyRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context patientallergy: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class PatientAllergyBeansConfig {

    @Bean
    public PatientAllergyPersistenceMapper patientAllergyPersistenceMapper() {
        return new PatientAllergyPersistenceMapper();
    }

    @Bean
    public PatientAllergyRepository patientAllergyRepository(PatientAllergyJpaRepository jpaRepository, PatientAllergyPersistenceMapper mapper) {
        return new PatientAllergyRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterPatientAllergyUseCase registerPatientAllergyUseCase(PatientAllergyRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterPatientAllergyUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdatePatientAllergyUseCase updatePatientAllergyUseCase(PatientAllergyRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdatePatientAllergyUseCase(repository, eventPublisher);
    }

    @Bean
    public DeletePatientAllergyUseCase deletePatientAllergyUseCase(PatientAllergyRepository repository, DomainEventPublisher eventPublisher) {
        return new DeletePatientAllergyUseCase(repository, eventPublisher);
    }

    @Bean
    public GetPatientAllergyByIdUseCase getPatientAllergyByIdUseCase(PatientAllergyRepository repository) {
        return new GetPatientAllergyByIdUseCase(repository);
    }

    @Bean
    public ListPatientAllergyUseCase listPatientAllergyUseCase(PatientAllergyRepository repository) {
        return new ListPatientAllergyUseCase(repository);
    }
}
