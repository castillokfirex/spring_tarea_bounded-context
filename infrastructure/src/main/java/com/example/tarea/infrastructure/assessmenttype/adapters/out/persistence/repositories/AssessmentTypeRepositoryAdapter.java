package com.example.tarea.infrastructure.assessmenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.assessmenttype.model.aggregate.AssessmentType;
import com.example.tarea.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.example.tarea.domain.assessmenttype.port.repository.AssessmentTypeRepository;
import com.example.tarea.infrastructure.assessmenttype.adapters.out.persistence.entity.AssessmentTypeJpaEntity;
import com.example.tarea.infrastructure.assessmenttype.adapters.out.persistence.mappers.AssessmentTypePersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto AssessmentTypeRepository con Spring Data JPA.
 */
public class AssessmentTypeRepositoryAdapter implements AssessmentTypeRepository {

    private final AssessmentTypeJpaRepository jpaRepository;
    private final AssessmentTypePersistenceMapper mapper;

    public AssessmentTypeRepositoryAdapter(AssessmentTypeJpaRepository jpaRepository, AssessmentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public AssessmentType save(AssessmentType assessmentType) {
        AssessmentTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(assessmentType));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AssessmentType> findById(AssessmentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<AssessmentType> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(AssessmentType assessmentType) {
        jpaRepository.deleteById(assessmentType.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, AssessmentTypeId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }
}
