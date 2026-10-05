package com.example.tarea.infrastructure.riskassessment.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.riskassessment.model.aggregate.RiskAssessment;
import com.example.tarea.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.example.tarea.domain.riskassessment.port.repository.RiskAssessmentRepository;
import com.example.tarea.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;
import com.example.tarea.infrastructure.riskassessment.adapters.out.persistence.mappers.RiskAssessmentPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto RiskAssessmentRepository con Spring Data JPA.
 */
public class RiskAssessmentRepositoryAdapter implements RiskAssessmentRepository {

    private final RiskAssessmentJpaRepository jpaRepository;
    private final RiskAssessmentPersistenceMapper mapper;

    public RiskAssessmentRepositoryAdapter(RiskAssessmentJpaRepository jpaRepository, RiskAssessmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public RiskAssessment save(RiskAssessment riskAssessment) {
        RiskAssessmentJpaEntity saved = jpaRepository.save(mapper.toJpa(riskAssessment));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<RiskAssessment> findById(RiskAssessmentId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<RiskAssessment> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(RiskAssessment riskAssessment) {
        jpaRepository.deleteById(riskAssessment.id().value());
    }
}
