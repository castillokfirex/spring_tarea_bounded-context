package com.example.tarea.infrastructure.clinicalnote.config;

import com.example.tarea.application.clinicalnote.usecase.DeleteClinicalNoteUseCase;
import com.example.tarea.application.clinicalnote.usecase.GetClinicalNoteByIdUseCase;
import com.example.tarea.application.clinicalnote.usecase.ListClinicalNoteUseCase;
import com.example.tarea.application.clinicalnote.usecase.RegisterClinicalNoteUseCase;
import com.example.tarea.application.clinicalnote.usecase.UpdateClinicalNoteUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.clinicalnote.port.repository.ClinicalNoteRepository;
import com.example.tarea.infrastructure.clinicalnote.adapters.out.persistence.mappers.ClinicalNotePersistenceMapper;
import com.example.tarea.infrastructure.clinicalnote.adapters.out.persistence.repositories.ClinicalNoteJpaRepository;
import com.example.tarea.infrastructure.clinicalnote.adapters.out.persistence.repositories.ClinicalNoteRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context clinicalnote: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ClinicalNoteBeansConfig {

    @Bean
    public ClinicalNotePersistenceMapper clinicalNotePersistenceMapper() {
        return new ClinicalNotePersistenceMapper();
    }

    @Bean
    public ClinicalNoteRepository clinicalNoteRepository(ClinicalNoteJpaRepository jpaRepository, ClinicalNotePersistenceMapper mapper) {
        return new ClinicalNoteRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterClinicalNoteUseCase registerClinicalNoteUseCase(ClinicalNoteRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterClinicalNoteUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateClinicalNoteUseCase updateClinicalNoteUseCase(ClinicalNoteRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateClinicalNoteUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteClinicalNoteUseCase deleteClinicalNoteUseCase(ClinicalNoteRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteClinicalNoteUseCase(repository, eventPublisher);
    }

    @Bean
    public GetClinicalNoteByIdUseCase getClinicalNoteByIdUseCase(ClinicalNoteRepository repository) {
        return new GetClinicalNoteByIdUseCase(repository);
    }

    @Bean
    public ListClinicalNoteUseCase listClinicalNoteUseCase(ClinicalNoteRepository repository) {
        return new ListClinicalNoteUseCase(repository);
    }
}
