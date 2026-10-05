package com.example.tarea.infrastructure.conversationstatus.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.example.tarea.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.example.tarea.domain.conversationstatus.port.repository.ConversationStatusRepository;
import com.example.tarea.infrastructure.conversationstatus.adapters.out.persistence.entity.ConversationStatusJpaEntity;
import com.example.tarea.infrastructure.conversationstatus.adapters.out.persistence.mappers.ConversationStatusPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ConversationStatusRepository con Spring Data JPA.
 */
public class ConversationStatusRepositoryAdapter implements ConversationStatusRepository {

    private final ConversationStatusJpaRepository jpaRepository;
    private final ConversationStatusPersistenceMapper mapper;

    public ConversationStatusRepositoryAdapter(ConversationStatusJpaRepository jpaRepository, ConversationStatusPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ConversationStatus save(ConversationStatus conversationStatus) {
        ConversationStatusJpaEntity saved = jpaRepository.save(mapper.toJpa(conversationStatus));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ConversationStatus> findById(ConversationStatusId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ConversationStatus> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ConversationStatus conversationStatus) {
        jpaRepository.deleteById(conversationStatus.id().value());
    }
}
