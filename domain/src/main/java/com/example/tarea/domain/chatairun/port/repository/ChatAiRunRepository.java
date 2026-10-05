package com.example.tarea.domain.chatairun.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatairun.model.aggregate.ChatAiRun;
import com.example.tarea.domain.chatairun.model.valueobject.ChatAiRunId;

/**
 * Puerto de salida (output port) para persistir el agregado ChatAiRun.
 */
public interface ChatAiRunRepository {

    ChatAiRun save(ChatAiRun chatAiRun);

    Optional<ChatAiRun> findById(ChatAiRunId id);

    List<ChatAiRun> findAll();

    void delete(ChatAiRun chatAiRun);
}
