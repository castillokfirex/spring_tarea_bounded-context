package com.example.tarea.infrastructure.escalationstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.example.tarea.domain.escalationstatus.model.valueobject.EscalationStatusId;
import com.example.tarea.domain.escalationstatus.port.repository.EscalationStatusRepository;
import com.example.tarea.infrastructure.escalationstatus.adapters.out.persistence.entity.EscalationStatusJpaEntity;
import com.example.tarea.infrastructure.escalationstatus.adapters.out.persistence.mappers.EscalationStatusPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto EscalationStatusRepository con Spring Data JPA.
 */
public class EscalationStatusRepositoryAdapter implements EscalationStatusRepository {

    private final EscalationStatusJpaRepository jpaRepository;
    private final EscalationStatusPersistenceMapper mapper;

    public EscalationStatusRepositoryAdapter(EscalationStatusJpaRepository jpaRepository, EscalationStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EscalationStatus save(EscalationStatus escalationStatus) {
        EscalationStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(escalationStatus));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<EscalationStatus> findById(EscalationStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<EscalationStatus> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(EscalationStatus escalationStatus) {
        jpaRepository.deleteById(escalationStatus.id().value());
    }
}
