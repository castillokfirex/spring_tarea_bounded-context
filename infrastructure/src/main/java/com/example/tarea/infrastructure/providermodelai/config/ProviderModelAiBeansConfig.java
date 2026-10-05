package com.example.tarea.infrastructure.providermodelai.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.providermodelai.usecase.DeleteProviderModelAiUseCase;
import com.example.tarea.application.providermodelai.usecase.GetProviderModelAiByIdUseCase;
import com.example.tarea.application.providermodelai.usecase.ListProviderModelAiUseCase;
import com.example.tarea.application.providermodelai.usecase.RegisterProviderModelAiUseCase;
import com.example.tarea.application.providermodelai.usecase.UpdateProviderModelAiUseCase;
import com.example.tarea.domain.providermodelai.port.repository.ProviderModelAiRepository;
import com.example.tarea.infrastructure.providermodelai.adapters.out.persistence.mappers.ProviderModelAiPersistenceMapper;
import com.example.tarea.infrastructure.providermodelai.adapters.out.persistence.repositories.ProviderModelAiJpaRepository;
import com.example.tarea.infrastructure.providermodelai.adapters.out.persistence.repositories.ProviderModelAiRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context providermodelai: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ProviderModelAiBeansConfig {

    @Bean
    public ProviderModelAiPersistenceMapper providerModelAiPersistenceMapper() {
        return new ProviderModelAiPersistenceMapper();
    }

    @Bean
    public ProviderModelAiRepository providerModelAiRepository(ProviderModelAiJpaRepository jpaRepository, ProviderModelAiPersistenceMapper mapper) {
        return new ProviderModelAiRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterProviderModelAiUseCase registerProviderModelAiUseCase(ProviderModelAiRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterProviderModelAiUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateProviderModelAiUseCase updateProviderModelAiUseCase(ProviderModelAiRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateProviderModelAiUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteProviderModelAiUseCase deleteProviderModelAiUseCase(ProviderModelAiRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteProviderModelAiUseCase(repository, eventPublisher);
    }

    @Bean
    public GetProviderModelAiByIdUseCase getProviderModelAiByIdUseCase(ProviderModelAiRepository repository) {
        return new GetProviderModelAiByIdUseCase(repository);
    }

    @Bean
    public ListProviderModelAiUseCase listProviderModelAiUseCase(ProviderModelAiRepository repository) {
        return new ListProviderModelAiUseCase(repository);
    }
}
