package com.example.tarea.infrastructure.professionaltype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.professionaltype.model.aggregate.ProfessionalType;
import com.example.tarea.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.example.tarea.domain.professionaltype.port.repository.ProfessionalTypeRepository;
import com.example.tarea.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;
import com.example.tarea.infrastructure.professionaltype.adapters.out.persistence.mappers.ProfessionalTypePersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ProfessionalTypeRepository con Spring Data JPA.
 */
public class ProfessionalTypeRepositoryAdapter implements ProfessionalTypeRepository {

    private final ProfessionalTypeJpaRepository jpaRepository;
    private final ProfessionalTypePersistenceMapper mapper;

    public ProfessionalTypeRepositoryAdapter(ProfessionalTypeJpaRepository jpaRepository, ProfessionalTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ProfessionalType save(ProfessionalType professionalType) {
        ProfessionalTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(professionalType));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ProfessionalType> findById(ProfessionalTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ProfessionalType> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ProfessionalType professionalType) {
        jpaRepository.deleteById(professionalType.id().value());
    }

    @Override
    public boolean existsByName(String name) {
        return jpaRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, ProfessionalTypeId id) {
        return jpaRepository.existsByNameAndIdNot(name, id.value());
    }
}
