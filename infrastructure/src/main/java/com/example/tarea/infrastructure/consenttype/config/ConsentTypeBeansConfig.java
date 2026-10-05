package com.example.tarea.infrastructure.consenttype.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.consenttype.usecase.DeleteConsentTypeUseCase;
import com.example.tarea.application.consenttype.usecase.GetConsentTypeByIdUseCase;
import com.example.tarea.application.consenttype.usecase.ListConsentTypeUseCase;
import com.example.tarea.application.consenttype.usecase.RegisterConsentTypeUseCase;
import com.example.tarea.application.consenttype.usecase.UpdateConsentTypeUseCase;
import com.example.tarea.domain.consenttype.port.repository.ConsentTypeRepository;
import com.example.tarea.infrastructure.consenttype.adapters.out.persistence.mappers.ConsentTypePersistenceMapper;
import com.example.tarea.infrastructure.consenttype.adapters.out.persistence.repositories.ConsentTypeJpaRepository;
import com.example.tarea.infrastructure.consenttype.adapters.out.persistence.repositories.ConsentTypeRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context consenttype: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ConsentTypeBeansConfig {

    @Bean
    public ConsentTypePersistenceMapper consentTypePersistenceMapper() {
        return new ConsentTypePersistenceMapper();
    }

    @Bean
    public ConsentTypeRepository consentTypeRepository(ConsentTypeJpaRepository jpaRepository, ConsentTypePersistenceMapper mapper) {
        return new ConsentTypeRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterConsentTypeUseCase registerConsentTypeUseCase(ConsentTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterConsentTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateConsentTypeUseCase updateConsentTypeUseCase(ConsentTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateConsentTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteConsentTypeUseCase deleteConsentTypeUseCase(ConsentTypeRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteConsentTypeUseCase(repository, eventPublisher);
    }

    @Bean
    public GetConsentTypeByIdUseCase getConsentTypeByIdUseCase(ConsentTypeRepository repository) {
        return new GetConsentTypeByIdUseCase(repository);
    }

    @Bean
    public ListConsentTypeUseCase listConsentTypeUseCase(ConsentTypeRepository repository) {
        return new ListConsentTypeUseCase(repository);
    }
}
