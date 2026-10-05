package com.example.tarea.application.providermodelai.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.providermodelai.command.RegisterProviderModelAiCommand;
import com.example.tarea.application.providermodelai.dto.ProviderModelAiResponse;
import com.example.tarea.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.example.tarea.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class RegisterProviderModelAiUseCase {

    private final ProviderModelAiRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterProviderModelAiUseCase(ProviderModelAiRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ProviderModelAiResponse execute(RegisterProviderModelAiCommand command) {

        ProviderModelAi providerModelAi = ProviderModelAi.register(
                command.nameProviderAi(),
                command.razonSocial(),
                command.sitioWeb(),
                command.isActive());

        ProviderModelAi saved = repository.save(providerModelAi);

        eventPublisher.publish(providerModelAi.domainEvents());
        providerModelAi.clearDomainEvents();

        return ProviderModelAiResponse.fromDomain(saved);
    }
}
