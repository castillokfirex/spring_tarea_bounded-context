package com.example.tarea.infrastructure.mentalstatusexam.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.mentalstatusexam.usecase.DeleteMentalStatusExamUseCase;
import com.example.tarea.application.mentalstatusexam.usecase.GetMentalStatusExamByIdUseCase;
import com.example.tarea.application.mentalstatusexam.usecase.ListMentalStatusExamUseCase;
import com.example.tarea.application.mentalstatusexam.usecase.RegisterMentalStatusExamUseCase;
import com.example.tarea.application.mentalstatusexam.usecase.UpdateMentalStatusExamUseCase;
import com.example.tarea.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;
import com.example.tarea.infrastructure.mentalstatusexam.adapters.out.persistence.mappers.MentalStatusExamPersistenceMapper;
import com.example.tarea.infrastructure.mentalstatusexam.adapters.out.persistence.repositories.MentalStatusExamJpaRepository;
import com.example.tarea.infrastructure.mentalstatusexam.adapters.out.persistence.repositories.MentalStatusExamRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context mentalstatusexam: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class MentalStatusExamBeansConfig {

    @Bean
    public MentalStatusExamPersistenceMapper mentalStatusExamPersistenceMapper() {
        return new MentalStatusExamPersistenceMapper();
    }

    @Bean
    public MentalStatusExamRepository mentalStatusExamRepository(MentalStatusExamJpaRepository jpaRepository, MentalStatusExamPersistenceMapper mapper) {
        return new MentalStatusExamRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterMentalStatusExamUseCase registerMentalStatusExamUseCase(MentalStatusExamRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterMentalStatusExamUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateMentalStatusExamUseCase updateMentalStatusExamUseCase(MentalStatusExamRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateMentalStatusExamUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteMentalStatusExamUseCase deleteMentalStatusExamUseCase(MentalStatusExamRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteMentalStatusExamUseCase(repository, eventPublisher);
    }

    @Bean
    public GetMentalStatusExamByIdUseCase getMentalStatusExamByIdUseCase(MentalStatusExamRepository repository) {
        return new GetMentalStatusExamByIdUseCase(repository);
    }

    @Bean
    public ListMentalStatusExamUseCase listMentalStatusExamUseCase(MentalStatusExamRepository repository) {
        return new ListMentalStatusExamUseCase(repository);
    }
}
