package com.example.tarea.infrastructure.sendertype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.sendertype.model.aggregate.SenderType;
import com.example.tarea.domain.sendertype.model.valueobject.SenderTypeId;
import com.example.tarea.domain.sendertype.port.repository.SenderTypeRepository;
import com.example.tarea.infrastructure.sendertype.adapters.out.persistence.entity.SenderTypeJpaEntity;
import com.example.tarea.infrastructure.sendertype.adapters.out.persistence.mappers.SenderTypePersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto SenderTypeRepository con Spring Data JPA.
 */
public class SenderTypeRepositoryAdapter implements SenderTypeRepository {

    private final SenderTypeJpaRepository jpaRepository;
    private final SenderTypePersistenceMapper mapper;

    public SenderTypeRepositoryAdapter(SenderTypeJpaRepository jpaRepository, SenderTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public SenderType save(SenderType senderType) {
        SenderTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(senderType));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<SenderType> findById(SenderTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<SenderType> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(SenderType senderType) {
        jpaRepository.deleteById(senderType.id().value());
    }
}
