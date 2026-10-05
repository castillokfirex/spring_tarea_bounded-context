package com.example.tarea.infrastructure.professional.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.professional.usecase.DeleteProfessionalUseCase;
import com.example.tarea.application.professional.usecase.GetProfessionalByIdUseCase;
import com.example.tarea.application.professional.usecase.ListProfessionalUseCase;
import com.example.tarea.application.professional.usecase.RegisterProfessionalUseCase;
import com.example.tarea.application.professional.usecase.UpdateProfessionalUseCase;
import com.example.tarea.domain.professional.port.repository.ProfessionalRepository;
import com.example.tarea.infrastructure.professional.adapters.out.persistence.mappers.ProfessionalPersistenceMapper;
import com.example.tarea.infrastructure.professional.adapters.out.persistence.repositories.ProfessionalJpaRepository;
import com.example.tarea.infrastructure.professional.adapters.out.persistence.repositories.ProfessionalRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context professional: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ProfessionalBeansConfig {

    @Bean
    public ProfessionalPersistenceMapper professionalPersistenceMapper() {
        return new ProfessionalPersistenceMapper();
    }

    @Bean
    public ProfessionalRepository professionalRepository(ProfessionalJpaRepository jpaRepository, ProfessionalPersistenceMapper mapper) {
        return new ProfessionalRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterProfessionalUseCase registerProfessionalUseCase(ProfessionalRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterProfessionalUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateProfessionalUseCase updateProfessionalUseCase(ProfessionalRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateProfessionalUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteProfessionalUseCase deleteProfessionalUseCase(ProfessionalRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteProfessionalUseCase(repository, eventPublisher);
    }

    @Bean
    public GetProfessionalByIdUseCase getProfessionalByIdUseCase(ProfessionalRepository repository) {
        return new GetProfessionalByIdUseCase(repository);
    }

    @Bean
    public ListProfessionalUseCase listProfessionalUseCase(ProfessionalRepository repository) {
        return new ListProfessionalUseCase(repository);
    }
}
