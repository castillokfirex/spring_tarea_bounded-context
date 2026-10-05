package com.example.tarea.application.providermodelai.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.providermodelai.command.UpdateProviderModelAiCommand;
import com.example.tarea.application.providermodelai.dto.ProviderModelAiResponse;
import com.example.tarea.domain.providermodelai.exception.ProviderModelAiNotFoundException;
import com.example.tarea.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.example.tarea.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class UpdateProviderModelAiUseCase {

    private final ProviderModelAiRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateProviderModelAiUseCase(ProviderModelAiRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ProviderModelAiResponse execute(UpdateProviderModelAiCommand command) {

        ProviderModelAi providerModelAi = repository.findById(command.id())
                .orElseThrow(() -> new ProviderModelAiNotFoundException(command.id()));

        providerModelAi.update(
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
