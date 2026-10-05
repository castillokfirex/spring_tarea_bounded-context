package com.example.tarea.application.stateregion.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.stateregion.command.RegisterStateRegionCommand;
import com.example.tarea.application.stateregion.dto.StateRegionResponse;
import com.example.tarea.domain.stateregion.model.aggregate.StateRegion;
import com.example.tarea.domain.stateregion.port.repository.StateRegionRepository;

public class RegisterStateRegionUseCase {

    private final StateRegionRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterStateRegionUseCase(StateRegionRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public StateRegionResponse execute(RegisterStateRegionCommand command) {

        StateRegion stateRegion = StateRegion.register(
                command.nameRegion(),
                command.codeRegion(),
                command.description(),
                command.isActive(),
                command.countryId());

        StateRegion saved = repository.save(stateRegion);

        eventPublisher.publish(stateRegion.domainEvents());
        stateRegion.clearDomainEvents();

        return StateRegionResponse.fromDomain(saved);
    }
}
