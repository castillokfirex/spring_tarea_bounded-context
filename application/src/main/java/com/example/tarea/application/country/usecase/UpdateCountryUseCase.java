package com.example.tarea.application.country.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.country.command.UpdateCountryCommand;
import com.example.tarea.application.country.dto.CountryResponse;
import com.example.tarea.domain.country.exception.CountryNotFoundException;
import com.example.tarea.domain.country.model.aggregate.Country;
import com.example.tarea.domain.country.port.repository.CountryRepository;

public class UpdateCountryUseCase {

    private final CountryRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateCountryUseCase(CountryRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public CountryResponse execute(UpdateCountryCommand command) {

        Country country = repository.findById(command.id())
                .orElseThrow(() -> new CountryNotFoundException(command.id()));

        country.update(
                command.nameCountry(),
                command.codeCountry(),
                command.description(),
                command.isActive(),
                command.telephonePrefix());

        Country saved = repository.save(country);

        eventPublisher.publish(country.domainEvents());
        country.clearDomainEvents();

        return CountryResponse.fromDomain(saved);
    }
}
