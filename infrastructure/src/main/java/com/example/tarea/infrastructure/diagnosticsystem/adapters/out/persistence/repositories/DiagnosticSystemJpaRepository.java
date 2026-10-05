package com.example.tarea.infrastructure.diagnosticsystem.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.diagnosticsystem.adapters.out.persistence.entity.DiagnosticSystemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticSystemJpaRepository extends JpaRepository<DiagnosticSystemJpaEntity, UUID> {

    boolean existsByCode(String code);

    boolean existsByCodeAndIdNot(String code, UUID id);
}
