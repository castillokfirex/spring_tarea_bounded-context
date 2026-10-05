package com.example.tarea.domain.chatescalationstatushistory.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.example.tarea.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

/**
 * Puerto de salida (output port) para persistir el agregado ChatEscalationStatusHistory.
 */
public interface ChatEscalationStatusHistoryRepository {

    ChatEscalationStatusHistory save(ChatEscalationStatusHistory chatEscalationStatusHistory);

    Optional<ChatEscalationStatusHistory> findById(ChatEscalationStatusHistoryId id);

    List<ChatEscalationStatusHistory> findAll();

    void delete(ChatEscalationStatusHistory chatEscalationStatusHistory);
}
