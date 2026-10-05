package com.example.tarea.infrastructure.professionaltype.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.professionaltype.usecase.DeleteProfessionalTypeUseCase;
import com.example.tarea.application.professionaltype.usecase.GetProfessionalTypeByIdUseCase;
import com.example.tarea.application.professionaltype.usecase.ListProfessionalTypeUseCase;
import com.example.tarea.application.professionaltype.usecase.RegisterProfessionalTypeUseCase;
import com.example.tarea.application.professionaltype.usecase.UpdateProfessionalTypeUseCase;
import com.example.tarea.domain.professionaltype.port.repository.ProfessionalTypeRepository;
import com.example.tarea.infrastructure.professionaltype.adapters.out.persistence.mappers.ProfessionalTypePersistenceMapper;
import com.example.tarea.infrastructure.professionaltype.adapters.out.persistence.repositories.ProfessionalTypeJpaRepository;
import com.example.tarea.infrastructure.professionaltype.adapters.out.persistence.repositories.ProfessionalTypeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context professionaltype: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ProfessionalTypeBeansConfig {

    @Bean
    public ProfessionalTypePersistenceMapper professionalTypePersistenceMapper() {
        return new ProfessionalTypePersistenceMapper();
    }

    @Bean
    public ProfessionalTypeRepository professionalTypeRepository(ProfessionalTypeJpaRepository jpaRepository, ProfessionalTypePersistenceMapper mapper) {
        return new ProfessionalTypeRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterProfessionalTypeUseCase registerProfessionalTypeUseCase(ProfessionalTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterProfessionalTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateProfessionalTypeUseCase updateProfessionalTypeUseCase(ProfessionalTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateProfessionalTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteProfessionalTypeUseCase deleteProfessionalTypeUseCase(ProfessionalTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteProfessionalTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public GetProfessionalTypeByIdUseCase getProfessionalTypeByIdUseCase(ProfessionalTypeRepository repository) {
        return new GetProfessionalTypeByIdUseCase(repository);
    }

    @Bean
    public ListProfessionalTypeUseCase listProfessionalTypeUseCase(ProfessionalTypeRepository repository) {
        return new ListProfessionalTypeUseCase(repository);
    }
}
