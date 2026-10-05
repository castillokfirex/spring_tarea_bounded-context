package com.example.tarea.infrastructure.messagetype.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.messagetype.model.aggregate.MessageType;
import com.example.tarea.domain.messagetype.model.valueobject.MessageTypeId;
import com.example.tarea.domain.messagetype.port.repository.MessageTypeRepository;
import com.example.tarea.infrastructure.messagetype.adapters.out.persistence.entity.MessageTypeJpaEntity;
import com.example.tarea.infrastructure.messagetype.adapters.out.persistence.mappers.MessageTypePersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto MessageTypeRepository con Spring Data JPA.
 */
public class MessageTypeRepositoryAdapter implements MessageTypeRepository {

    private final MessageTypeJpaRepository jpaRepository;
    private final MessageTypePersistenceMapper mapper;

    public MessageTypeRepositoryAdapter(MessageTypeJpaRepository jpaRepository, MessageTypePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public MessageType save(MessageType messageType) {
        MessageTypeJpaEntity saved = jpaRepository.save(mapper.toJpa(messageType));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<MessageType> findById(MessageTypeId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<MessageType> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(MessageType messageType) {
        jpaRepository.deleteById(messageType.id().value());
    }
}
