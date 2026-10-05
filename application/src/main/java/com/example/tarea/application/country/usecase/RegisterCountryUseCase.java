package com.example.tarea.application.country.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.country.command.RegisterCountryCommand;
import com.example.tarea.application.country.dto.CountryResponse;
import com.example.tarea.domain.country.model.aggregate.Country;
import com.example.tarea.domain.country.port.repository.CountryRepository;

public class RegisterCountryUseCase {

    private final CountryRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterCountryUseCase(CountryRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public CountryResponse execute(RegisterCountryCommand command) {

        Country country = Country.register(
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
