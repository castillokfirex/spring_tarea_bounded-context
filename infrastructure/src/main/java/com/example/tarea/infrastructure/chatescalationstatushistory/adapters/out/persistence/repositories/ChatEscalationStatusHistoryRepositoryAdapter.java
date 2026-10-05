package com.example.tarea.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.example.tarea.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.example.tarea.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;
import com.example.tarea.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity.ChatEscalationStatusHistoryJpaEntity;
import com.example.tarea.infrastructure.chatescalationstatushistory.adapters.out.persistence.mappers.ChatEscalationStatusHistoryPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ChatEscalationStatusHistoryRepository con Spring Data JPA.
 */
public class ChatEscalationStatusHistoryRepositoryAdapter implements ChatEscalationStatusHistoryRepository {

    private final ChatEscalationStatusHistoryJpaRepository jpaRepository;
    private final ChatEscalationStatusHistoryPersistenceMapper mapper;

    public ChatEscalationStatusHistoryRepositoryAdapter(ChatEscalationStatusHistoryJpaRepository jpaRepository, ChatEscalationStatusHistoryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalationStatusHistory save(ChatEscalationStatusHistory chatEscalationStatusHistory) {
        ChatEscalationStatusHistoryJpaEntity saved = jpaRepository.save(mapper.toJpa(chatEscalationStatusHistory));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ChatEscalationStatusHistory> findById(ChatEscalationStatusHistoryId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatEscalationStatusHistory> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ChatEscalationStatusHistory chatEscalationStatusHistory) {
        jpaRepository.deleteById(chatEscalationStatusHistory.id().value());
    }
}
