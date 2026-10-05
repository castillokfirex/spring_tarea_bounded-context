package com.example.tarea.infrastructure.chatairunerror.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.example.tarea.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import com.example.tarea.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;
import com.example.tarea.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;
import com.example.tarea.infrastructure.chatairunerror.adapters.out.persistence.mappers.ChatAiRunErrorPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ChatAiRunErrorRepository con Spring Data JPA.
 */
public class ChatAiRunErrorRepositoryAdapter implements ChatAiRunErrorRepository {

    private final ChatAiRunErrorJpaRepository jpaRepository;
    private final ChatAiRunErrorPersistenceMapper mapper;

    public ChatAiRunErrorRepositoryAdapter(ChatAiRunErrorJpaRepository jpaRepository, ChatAiRunErrorPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatAiRunError save(ChatAiRunError chatAiRunError) {
        ChatAiRunErrorJpaEntity saved = jpaRepository.save(mapper.toJpa(chatAiRunError));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ChatAiRunError> findById(ChatAiRunErrorId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatAiRunError> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ChatAiRunError chatAiRunError) {
        jpaRepository.deleteById(chatAiRunError.id().value());
    }
}
