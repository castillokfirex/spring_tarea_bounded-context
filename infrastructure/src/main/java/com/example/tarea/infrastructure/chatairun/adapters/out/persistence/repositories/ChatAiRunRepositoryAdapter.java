package com.example.tarea.infrastructure.chatairun.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatairun.model.aggregate.ChatAiRun;
import com.example.tarea.domain.chatairun.model.valueobject.ChatAiRunId;
import com.example.tarea.domain.chatairun.port.repository.ChatAiRunRepository;
import com.example.tarea.infrastructure.chatairun.adapters.out.persistence.entity.ChatAiRunJpaEntity;
import com.example.tarea.infrastructure.chatairun.adapters.out.persistence.mappers.ChatAiRunPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ChatAiRunRepository con Spring Data JPA.
 */
public class ChatAiRunRepositoryAdapter implements ChatAiRunRepository {

    private final ChatAiRunJpaRepository jpaRepository;
    private final ChatAiRunPersistenceMapper mapper;

    public ChatAiRunRepositoryAdapter(ChatAiRunJpaRepository jpaRepository, ChatAiRunPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatAiRun save(ChatAiRun chatAiRun) {
        ChatAiRunJpaEntity saved = jpaRepository.save(mapper.toJpa(chatAiRun));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ChatAiRun> findById(ChatAiRunId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatAiRun> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ChatAiRun chatAiRun) {
        jpaRepository.deleteById(chatAiRun.id().value());
    }
}
