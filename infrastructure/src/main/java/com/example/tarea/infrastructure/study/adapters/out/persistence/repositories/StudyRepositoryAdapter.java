package com.example.tarea.infrastructure.study.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.study.model.aggregate.Study;
import com.example.tarea.domain.study.model.valueobject.StudyId;
import com.example.tarea.domain.study.port.repository.StudyRepository;
import com.example.tarea.infrastructure.study.adapters.out.persistence.entity.StudyJpaEntity;
import com.example.tarea.infrastructure.study.adapters.out.persistence.mappers.StudyPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto StudyRepository con Spring Data JPA.
 */
public class StudyRepositoryAdapter implements StudyRepository {

    private final StudyJpaRepository jpaRepository;
    private final StudyPersistenceMapper mapper;

    public StudyRepositoryAdapter(StudyJpaRepository jpaRepository, StudyPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Study save(Study study) {
        StudyJpaEntity saved = jpaRepository.save(mapper.toJpa(study));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Study> findById(StudyId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Study> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Study study) {
        jpaRepository.deleteById(study.id().value());
    }
}
