package com.example.tarea.infrastructure.consenttype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.consenttype.model.aggregate.ConsentType;
import com.example.tarea.domain.consenttype.model.valueobject.ConsentTypeId;
import com.example.tarea.domain.consenttype.port.repository.ConsentTypeRepository;
import com.example.tarea.infrastructure.consenttype.adapters.out.persistence.entity.ConsentTypeJpaEntity;
import com.example.tarea.infrastructure.consenttype.adapters.out.persistence.mappers.ConsentTypePersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ConsentTypeRepository con Spring Data JPA.
 */
public class ConsentTypeRepositoryAdapter implements ConsentTypeRepository {

    private final ConsentTypeJpaRepository jpaRepository;
    private final ConsentTypePersistenceMapper mapper;

    public ConsentTypeRepositoryAdapter(ConsentTypeJpaRepository jpaRepository, ConsentTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ConsentType save(ConsentType consentType) {
        ConsentTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(consentType));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ConsentType> findById(ConsentTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ConsentType> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ConsentType consentType) {
        jpaRepository.deleteById(consentType.id().value());
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }

    @Override
    public boolean existsByCodeAndIdNot(String code, ConsentTypeId id) {
        return jpaRepository.existsByCodeAndIdNot(code, id.value());
    }
}
