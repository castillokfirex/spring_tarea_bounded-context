package com.example.tarea.domain.conversationstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.conversationstatus.model.aggregate.ConversationStatus;
import com.example.tarea.domain.conversationstatus.model.valueobject.ConversationStatusId;

/**
 * Puerto de salida (output port) para persistir el agregado ConversationStatus.
 */
public interface ConversationStatusRepository {

    ConversationStatus save(ConversationStatus conversationStatus);

    Optional<ConversationStatus> findById(ConversationStatusId id);

    List<ConversationStatus> findAll();

    void delete(ConversationStatus conversationStatus);
}
