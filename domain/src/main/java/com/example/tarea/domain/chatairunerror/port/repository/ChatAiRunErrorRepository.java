package com.example.tarea.domain.chatairunerror.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatairunerror.model.aggregate.ChatAiRunError;
import com.example.tarea.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

/**
 * Puerto de salida (output port) para persistir el agregado ChatAiRunError.
 */
public interface ChatAiRunErrorRepository {

    ChatAiRunError save(ChatAiRunError chatAiRunError);

    Optional<ChatAiRunError> findById(ChatAiRunErrorId id);

    List<ChatAiRunError> findAll();

    void delete(ChatAiRunError chatAiRunError);
}
