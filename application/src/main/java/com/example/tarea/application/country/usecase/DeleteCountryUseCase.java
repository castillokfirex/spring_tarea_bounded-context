package com.example.tarea.application.country.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.country.exception.CountryNotFoundException;
import com.example.tarea.domain.country.model.aggregate.Country;
import com.example.tarea.domain.country.model.valueobject.CountryId;
import com.example.tarea.domain.country.port.repository.CountryRepository;

public class DeleteCountryUseCase {

    private final CountryRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteCountryUseCase(CountryRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(CountryId id) {

        Country country = repository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));

        country.delete();
        repository.delete(country);

        eventPublisher.publish(country.domainEvents());
        country.clearDomainEvents();
    }
}
