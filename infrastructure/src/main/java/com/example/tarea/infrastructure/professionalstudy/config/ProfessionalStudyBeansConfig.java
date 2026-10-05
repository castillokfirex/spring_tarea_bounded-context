package com.example.tarea.infrastructure.professionalstudy.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.professionalstudy.usecase.DeleteProfessionalStudyUseCase;
import com.example.tarea.application.professionalstudy.usecase.GetProfessionalStudyByIdUseCase;
import com.example.tarea.application.professionalstudy.usecase.ListProfessionalStudyUseCase;
import com.example.tarea.application.professionalstudy.usecase.RegisterProfessionalStudyUseCase;
import com.example.tarea.application.professionalstudy.usecase.UpdateProfessionalStudyUseCase;
import com.example.tarea.domain.professionalstudy.port.repository.ProfessionalStudyRepository;
import com.example.tarea.infrastructure.professionalstudy.adapters.out.persistence.mappers.ProfessionalStudyPersistenceMapper;
import com.example.tarea.infrastructure.professionalstudy.adapters.out.persistence.repositories.ProfessionalStudyJpaRepository;
import com.example.tarea.infrastructure.professionalstudy.adapters.out.persistence.repositories.ProfessionalStudyRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context professionalstudy: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ProfessionalStudyBeansConfig {

    @Bean
    public ProfessionalStudyPersistenceMapper professionalStudyPersistenceMapper() {
        return new ProfessionalStudyPersistenceMapper();
    }

    @Bean
    public ProfessionalStudyRepository professionalStudyRepository(ProfessionalStudyJpaRepository jpaRepository, ProfessionalStudyPersistenceMapper mapper) {
        return new ProfessionalStudyRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterProfessionalStudyUseCase registerProfessionalStudyUseCase(ProfessionalStudyRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterProfessionalStudyUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateProfessionalStudyUseCase updateProfessionalStudyUseCase(ProfessionalStudyRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateProfessionalStudyUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteProfessionalStudyUseCase deleteProfessionalStudyUseCase(ProfessionalStudyRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteProfessionalStudyUseCase(repository, eventPublisher);
    }

    @Bean
    public GetProfessionalStudyByIdUseCase getProfessionalStudyByIdUseCase(ProfessionalStudyRepository repository) {
        return new GetProfessionalStudyByIdUseCase(repository);
    }

    @Bean
    public ListProfessionalStudyUseCase listProfessionalStudyUseCase(ProfessionalStudyRepository repository) {
        return new ListProfessionalStudyUseCase(repository);
    }
}
