package com.example.tarea.application.providermodelai.usecase;

import com.example.tarea.application.providermodelai.dto.ProviderModelAiResponse;
import com.example.tarea.domain.providermodelai.exception.ProviderModelAiNotFoundException;
import com.example.tarea.domain.providermodelai.model.valueobject.ProviderModelAiId;
import com.example.tarea.domain.providermodelai.port.repository.ProviderModelAiRepository;

public class GetProviderModelAiByIdUseCase {

    private final ProviderModelAiRepository repository;

    public GetProviderModelAiByIdUseCase(ProviderModelAiRepository repository) {
        this.repository = repository;
    }

    public ProviderModelAiResponse execute(ProviderModelAiId id) {
        return repository.findById(id)
                .map(ProviderModelAiResponse::fromDomain)
                .orElseThrow(() -> new ProviderModelAiNotFoundException(id));
    }
}
