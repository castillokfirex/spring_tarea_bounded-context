package com.example.tarea.domain.airunstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.airunstatus.model.aggregate.AiRunStatus;
import com.example.tarea.domain.airunstatus.model.valueobject.AiRunStatusId;

/**
 * Puerto de salida (output port) para persistir el agregado AiRunStatus.
 */
public interface AiRunStatusRepository {

    AiRunStatus save(AiRunStatus aiRunStatus);

    Optional<AiRunStatus> findById(AiRunStatusId id);

    List<AiRunStatus> findAll();

    void delete(AiRunStatus aiRunStatus);
}
