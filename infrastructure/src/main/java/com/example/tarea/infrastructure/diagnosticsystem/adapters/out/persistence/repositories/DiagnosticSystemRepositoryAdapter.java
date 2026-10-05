package com.example.tarea.infrastructure.diagnosticsystem.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import com.example.tarea.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import com.example.tarea.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;
import com.example.tarea.infrastructure.diagnosticsystem.adapters.out.persistence.entity.DiagnosticSystemJpaEntity;
import com.example.tarea.infrastructure.diagnosticsystem.adapters.out.persistence.mappers.DiagnosticSystemPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto DiagnosticSystemRepository con Spring Data JPA.
 */
public class DiagnosticSystemRepositoryAdapter implements DiagnosticSystemRepository {

    private final DiagnosticSystemJpaRepository jpaRepository;
    private final DiagnosticSystemPersistenceMapper mapper;

    public DiagnosticSystemRepositoryAdapter(DiagnosticSystemJpaRepository jpaRepository, DiagnosticSystemPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public DiagnosticSystem save(DiagnosticSystem diagnosticSystem) {
        DiagnosticSystemJpaEntity saved = jpaRepository.save(mapper.toJpa(diagnosticSystem));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<DiagnosticSystem> findById(DiagnosticSystemId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<DiagnosticSystem> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(DiagnosticSystem diagnosticSystem) {
        jpaRepository.deleteById(diagnosticSystem.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, DiagnosticSystemId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }
}
