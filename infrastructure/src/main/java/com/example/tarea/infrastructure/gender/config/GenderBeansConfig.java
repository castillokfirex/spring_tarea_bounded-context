package com.example.tarea.infrastructure.gender.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.gender.usecase.DeleteGenderUseCase;
import com.example.tarea.application.gender.usecase.GetGenderByIdUseCase;
import com.example.tarea.application.gender.usecase.ListGenderUseCase;
import com.example.tarea.application.gender.usecase.RegisterGenderUseCase;
import com.example.tarea.application.gender.usecase.UpdateGenderUseCase;
import com.example.tarea.domain.gender.port.repository.GenderRepository;
import com.example.tarea.infrastructure.gender.adapters.out.persistence.mappers.GenderPersistenceMapper;
import com.example.tarea.infrastructure.gender.adapters.out.persistence.repositories.GenderJpaRepository;
import com.example.tarea.infrastructure.gender.adapters.out.persistence.repositories.GenderRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context gender: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class GenderBeansConfig {

    @Bean
    public GenderPersistenceMapper genderPersistenceMapper() {
        return new GenderPersistenceMapper();
    }

    @Bean
    public GenderRepository genderRepository(GenderJpaRepository jpaRepository, GenderPersistenceMapper mapper) {
        return new GenderRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterGenderUseCase registerGenderUseCase(GenderRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterGenderUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateGenderUseCase updateGenderUseCase(GenderRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateGenderUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteGenderUseCase deleteGenderUseCase(GenderRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteGenderUseCase(repository, eventPublisher);
    }

    @Bean
    public GetGenderByIdUseCase getGenderByIdUseCase(GenderRepository repository) {
        return new GetGenderByIdUseCase(repository);
    }

    @Bean
    public ListGenderUseCase listGenderUseCase(GenderRepository repository) {
        return new ListGenderUseCase(repository);
    }
}
