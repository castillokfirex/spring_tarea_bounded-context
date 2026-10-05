package com.example.tarea.application.citymunicipality.usecase;

import com.example.tarea.application.citymunicipality.command.RegisterCityMunicipalityCommand;
import com.example.tarea.application.citymunicipality.dto.CityMunicipalityResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.example.tarea.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class RegisterCityMunicipalityUseCase {

    private final CityMunicipalityRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterCityMunicipalityUseCase(CityMunicipalityRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public CityMunicipalityResponse execute(RegisterCityMunicipalityCommand command) {

        CityMunicipality cityMunicipality = CityMunicipality.register(
                command.nameCity(),
                command.codeCity(),
                command.description(),
                command.isActive(),
                command.regionId());

        CityMunicipality saved = repository.save(cityMunicipality);

        eventPublisher.publish(cityMunicipality.domainEvents());
        cityMunicipality.clearDomainEvents();

        return CityMunicipalityResponse.fromDomain(saved);
    }
}
