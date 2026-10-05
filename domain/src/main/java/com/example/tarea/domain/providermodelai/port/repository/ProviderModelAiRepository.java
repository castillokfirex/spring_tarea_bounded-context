package com.example.tarea.domain.providermodelai.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.providermodelai.model.aggregate.ProviderModelAi;
import com.example.tarea.domain.providermodelai.model.valueobject.ProviderModelAiId;

/**
 * Puerto de salida (output port) para persistir el agregado ProviderModelAi.
 */
public interface ProviderModelAiRepository {

    ProviderModelAi save(ProviderModelAi providerModelAi);

    Optional<ProviderModelAi> findById(ProviderModelAiId id);

    List<ProviderModelAi> findAll();

    void delete(ProviderModelAi providerModelAi);
}
