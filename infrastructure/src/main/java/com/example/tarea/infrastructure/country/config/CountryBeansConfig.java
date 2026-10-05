package com.example.tarea.infrastructure.country.config;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.country.usecase.DeleteCountryUseCase;
import com.example.tarea.application.country.usecase.GetCountryByIdUseCase;
import com.example.tarea.application.country.usecase.ListCountryUseCase;
import com.example.tarea.application.country.usecase.RegisterCountryUseCase;
import com.example.tarea.application.country.usecase.UpdateCountryUseCase;
import com.example.tarea.domain.country.port.repository.CountryRepository;
import com.example.tarea.infrastructure.country.adapters.out.persistence.mappers.CountryPersistenceMapper;
import com.example.tarea.infrastructure.country.adapters.out.persistence.repositories.CountryJpaRepository;
import com.example.tarea.infrastructure.country.adapters.out.persistence.repositories.CountryRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context country: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class CountryBeansConfig {

    @Bean
    public CountryPersistenceMapper countryPersistenceMapper() {
        return new CountryPersistenceMapper();
    }

    @Bean
    public CountryRepository countryRepository(CountryJpaRepository jpaRepository, CountryPersistenceMapper mapper) {
        return new CountryRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterCountryUseCase registerCountryUseCase(CountryRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterCountryUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateCountryUseCase updateCountryUseCase(CountryRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateCountryUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteCountryUseCase deleteCountryUseCase(CountryRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteCountryUseCase(repository, eventPublisher);
    }

    @Bean
    public GetCountryByIdUseCase getCountryByIdUseCase(CountryRepository repository) {
        return new GetCountryByIdUseCase(repository);
    }

    @Bean
    public ListCountryUseCase listCountryUseCase(CountryRepository repository) {
        return new ListCountryUseCase(repository);
    }
}
