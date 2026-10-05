package com.example.tarea.infrastructure.chatairunmetric.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.example.tarea.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.example.tarea.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;
import com.example.tarea.infrastructure.chatairunmetric.adapters.out.persistence.entity.ChatAiRunMetricJpaEntity;
import com.example.tarea.infrastructure.chatairunmetric.adapters.out.persistence.mappers.ChatAiRunMetricPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ChatAiRunMetricRepository con Spring Data JPA.
 */
public class ChatAiRunMetricRepositoryAdapter implements ChatAiRunMetricRepository {

    private final ChatAiRunMetricJpaRepository jpaRepository;
    private final ChatAiRunMetricPersistenceMapper mapper;

    public ChatAiRunMetricRepositoryAdapter(ChatAiRunMetricJpaRepository jpaRepository, ChatAiRunMetricPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatAiRunMetric save(ChatAiRunMetric chatAiRunMetric) {
        ChatAiRunMetricJpaEntity saved = jpaRepository.save(mapper.toJpa(chatAiRunMetric));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ChatAiRunMetric> findById(ChatAiRunMetricId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatAiRunMetric> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ChatAiRunMetric chatAiRunMetric) {
        jpaRepository.deleteById(chatAiRunMetric.id().value());
    }
}
