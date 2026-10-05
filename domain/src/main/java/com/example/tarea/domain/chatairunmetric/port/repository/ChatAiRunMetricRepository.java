package com.example.tarea.domain.chatairunmetric.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.example.tarea.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

/**
 * Puerto de salida (output port) para persistir el agregado ChatAiRunMetric.
 */
public interface ChatAiRunMetricRepository {

    ChatAiRunMetric save(ChatAiRunMetric chatAiRunMetric);

    Optional<ChatAiRunMetric> findById(ChatAiRunMetricId id);

    List<ChatAiRunMetric> findAll();

    void delete(ChatAiRunMetric chatAiRunMetric);
}
