package com.example.tarea.infrastructure.chatescalation.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatescalation.model.aggregate.ChatEscalation;
import com.example.tarea.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.example.tarea.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.example.tarea.infrastructure.chatescalation.adapters.out.persistence.entity.ChatEscalationJpaEntity;
import com.example.tarea.infrastructure.chatescalation.adapters.out.persistence.mappers.ChatEscalationPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ChatEscalationRepository con Spring Data JPA.
 */
public class ChatEscalationRepositoryAdapter implements ChatEscalationRepository {

    private final ChatEscalationJpaRepository jpaRepository;
    private final ChatEscalationPersistenceMapper mapper;

    public ChatEscalationRepositoryAdapter(ChatEscalationJpaRepository jpaRepository, ChatEscalationPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalation save(ChatEscalation chatEscalation) {
        ChatEscalationJpaEntity saved = jpaRepository.save(mapper.toJpa(chatEscalation));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ChatEscalation> findById(ChatEscalationId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatEscalation> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ChatEscalation chatEscalation) {
        jpaRepository.deleteById(chatEscalation.id().value());
    }
}
