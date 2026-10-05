package com.example.tarea.application.providermodelai.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.providermodelai.exception.ProviderModelAiNotFoundException;
import com.example.tarea.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.example.tarea.domain.providermodelai.model.valueobject.ProviderModelAiId;
import com.example.tarea.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class DeleteProviderModelAiUseCase {

    private final ProviderModelAiRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteProviderModelAiUseCase(ProviderModelAiRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ProviderModelAiId id) {

        ProviderModelAi providerModelAi = repository.findById(id)
                .orElseThrow(() -> new ProviderModelAiNotFoundException(id));

        providerModelAi.delete();
        repository.delete(providerModelAi);

        eventPublisher.publish(providerModelAi.domainEvents());
        providerModelAi.clearDomainEvents();
    }
}
