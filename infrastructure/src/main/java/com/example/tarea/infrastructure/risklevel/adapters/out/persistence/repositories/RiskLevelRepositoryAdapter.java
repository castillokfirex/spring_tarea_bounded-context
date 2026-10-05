package com.example.tarea.infrastructure.risklevel.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.risklevel.model.aggregate.RiskLevel;
import com.example.tarea.domain.risklevel.model.valueobject.RiskLevelId;
import com.example.tarea.domain.risklevel.port.repository.RiskLevelRepository;
import com.example.tarea.infrastructure.risklevel.adapters.out.persistence.entity.RiskLevelJpaEntity;
import com.example.tarea.infrastructure.risklevel.adapters.out.persistence.mappers.RiskLevelPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto RiskLevelRepository con Spring Data JPA.
 */
public class RiskLevelRepositoryAdapter implements RiskLevelRepository {

    private final RiskLevelJpaRepository jpaRepository;
    private final RiskLevelPersistenceMapper mapper;

    public RiskLevelRepositoryAdapter(RiskLevelJpaRepository jpaRepository, RiskLevelPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public RiskLevel save(RiskLevel riskLevel) {
        RiskLevelJpaEntity saved = jpaRepository.save(mapper.toJpa(riskLevel));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<RiskLevel> findById(RiskLevelId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<RiskLevel> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(RiskLevel riskLevel) {
        jpaRepository.deleteById(riskLevel.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, RiskLevelId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }
}
