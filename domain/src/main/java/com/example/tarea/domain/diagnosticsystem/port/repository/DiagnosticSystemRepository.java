package com.example.tarea.domain.diagnosticsystem.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.example.tarea.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

/**
 * Puerto de salida (output port) para persistir el agregado DiagnosticSystem.
 */
public interface DiagnosticSystemRepository {

    DiagnosticSystem save(DiagnosticSystem diagnosticSystem);

    Optional<DiagnosticSystem> findById(DiagnosticSystemId id);

    List<DiagnosticSystem> findAll();

    void delete(DiagnosticSystem diagnosticSystem);

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, DiagnosticSystemId id);
}
