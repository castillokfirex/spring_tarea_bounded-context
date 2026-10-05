package com.example.tarea.domain.chatescalation.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatescalation.model.aggregate.ChatEscalation;
import com.example.tarea.domain.chatescalation.model.valueobject.ChatEscalationId;

/**
 * Puerto de salida (output port) para persistir el agregado ChatEscalation.
 */
public interface ChatEscalationRepository {

    ChatEscalation save(ChatEscalation chatEscalation);

    Optional<ChatEscalation> findById(ChatEscalationId id);

    List<ChatEscalation> findAll();

    void delete(ChatEscalation chatEscalation);
}
