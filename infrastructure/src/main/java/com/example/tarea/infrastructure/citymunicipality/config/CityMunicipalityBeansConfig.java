package com.example.tarea.infrastructure.citymunicipality.config;

import com.example.tarea.application.citymunicipality.usecase.DeleteCityMunicipalityUseCase;
import com.example.tarea.application.citymunicipality.usecase.GetCityMunicipalityByIdUseCase;
import com.example.tarea.application.citymunicipality.usecase.ListCityMunicipalityUseCase;
import com.example.tarea.application.citymunicipality.usecase.RegisterCityMunicipalityUseCase;
import com.example.tarea.application.citymunicipality.usecase.UpdateCityMunicipalityUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import com.example.tarea.infrastructure.citymunicipality.adapters.out.persistence.mappers.CityMunicipalityPersistenceMapper;
import com.example.tarea.infrastructure.citymunicipality.adapters.out.persistence.repositories.CityMunicipalityJpaRepository;
import com.example.tarea.infrastructure.citymunicipality.adapters.out.persistence.repositories.CityMunicipalityRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context citymunicipality: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class CityMunicipalityBeansConfig {

    @Bean
    public CityMunicipalityPersistenceMapper cityMunicipalityPersistenceMapper() {
        return new CityMunicipalityPersistenceMapper();
    }

    @Bean
    public CityMunicipalityRepository cityMunicipalityRepository(CityMunicipalityJpaRepository jpaRepository, CityMunicipalityPersistenceMapper mapper) {
        return new CityMunicipalityRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterCityMunicipalityUseCase registerCityMunicipalityUseCase(CityMunicipalityRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterCityMunicipalityUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateCityMunicipalityUseCase updateCityMunicipalityUseCase(CityMunicipalityRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateCityMunicipalityUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteCityMunicipalityUseCase deleteCityMunicipalityUseCase(CityMunicipalityRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteCityMunicipalityUseCase(repository, eventPublisher);
    }

    @Bean
    public GetCityMunicipalityByIdUseCase getCityMunicipalityByIdUseCase(CityMunicipalityRepository repository) {
        return new GetCityMunicipalityByIdUseCase(repository);
    }

    @Bean
    public ListCityMunicipalityUseCase listCityMunicipalityUseCase(CityMunicipalityRepository repository) {
        return new ListCityMunicipalityUseCase(repository);
    }
}
