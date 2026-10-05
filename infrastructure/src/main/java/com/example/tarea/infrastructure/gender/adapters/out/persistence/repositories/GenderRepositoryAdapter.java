package com.example.tarea.infrastructure.gender.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.gender.model.aggregate.Gender;
import com.example.tarea.domain.gender.model.valueobject.GenderId;
import com.example.tarea.domain.gender.port.repository.GenderRepository;
import com.example.tarea.infrastructure.gender.adapters.out.persistence.entity.GenderJpaEntity;
import com.example.tarea.infrastructure.gender.adapters.out.persistence.mappers.GenderPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto GenderRepository con Spring Data JPA.
 */
public class GenderRepositoryAdapter implements GenderRepository {

    private final GenderJpaRepository jpaRepository;
    private final GenderPersistenceMapper mapper;

    public GenderRepositoryAdapter(GenderJpaRepository jpaRepository, GenderPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Gender save(Gender gender) {
        GenderJpaEntity saved = jpaRepository.save(mapper.toJpa(gender));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Gender> findById(GenderId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Gender> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Gender gender) {
        jpaRepository.deleteById(gender.id().value());
    }

    @Override
    public boolean existsByDescription(String description) {
        return jpaRepository.existsByDescription(description);
    }

    @Override
    public boolean existsByDescriptionAndIdNot(String description, GenderId id) {
        return jpaRepository.existsByDescriptionAndIdNot(description, id.value());
    }
}
