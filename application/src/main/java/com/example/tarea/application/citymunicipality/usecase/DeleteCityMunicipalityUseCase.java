package com.example.tarea.application.citymunicipality.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.citymunicipality.exception.CityMunicipalityNotFoundException;
import com.example.tarea.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.example.tarea.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.example.tarea.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class DeleteCityMunicipalityUseCase {

    private final CityMunicipalityRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteCityMunicipalityUseCase(CityMunicipalityRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(CityMunicipalityId id) {

        CityMunicipality cityMunicipality = repository.findById(id)
                .orElseThrow(() -> new CityMunicipalityNotFoundException(id));

        cityMunicipality.delete();
        repository.delete(cityMunicipality);

        eventPublisher.publish(cityMunicipality.domainEvents());
        cityMunicipality.clearDomainEvents();
    }
}
