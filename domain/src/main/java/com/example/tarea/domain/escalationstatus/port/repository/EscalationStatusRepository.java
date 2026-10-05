package com.example.tarea.domain.escalationstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.example.tarea.domain.escalationstatus.model.valueobject.EscalationStatusId;

/**
 * Puerto de salida (output port) para persistir el agregado EscalationStatus.
 */
public interface EscalationStatusRepository {

    EscalationStatus save(EscalationStatus escalationStatus);

    Optional<EscalationStatus> findById(EscalationStatusId id);

    List<EscalationStatus> findAll();

    void delete(EscalationStatus escalationStatus);
}
