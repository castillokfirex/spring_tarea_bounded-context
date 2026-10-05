package com.example.tarea.domain.aimodel.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.aimodel.model.aggregate.AiModel;
import com.example.tarea.domain.aimodel.model.valueobject.AiModelId;

/**
 * Puerto de salida (output port) para persistir el agregado AiModel.
 */
public interface AiModelRepository {

    AiModel save(AiModel aiModel);

    Optional<AiModel> findById(AiModelId id);

    List<AiModel> findAll();

    void delete(AiModel aiModel);
}
