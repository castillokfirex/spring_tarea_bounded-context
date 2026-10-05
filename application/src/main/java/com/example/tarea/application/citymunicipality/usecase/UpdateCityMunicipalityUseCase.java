package com.example.tarea.application.citymunicipality.usecase;

import com.example.tarea.application.citymunicipality.command.UpdateCityMunicipalityCommand;
import com.example.tarea.application.citymunicipality.dto.CityMunicipalityResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.citymunicipality.exception.CityMunicipalityNotFoundException;
import com.example.tarea.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.example.tarea.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class UpdateCityMunicipalityUseCase {

    private final CityMunicipalityRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateCityMunicipalityUseCase(CityMunicipalityRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public CityMunicipalityResponse execute(UpdateCityMunicipalityCommand command) {

        CityMunicipality cityMunicipality = repository.findById(command.id())
                .orElseThrow(() -> new CityMunicipalityNotFoundException(command.id()));

        cityMunicipality.update(
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
