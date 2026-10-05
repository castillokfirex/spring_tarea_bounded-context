package com.example.tarea.domain.chatescalationassignment.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.example.tarea.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

/**
 * Puerto de salida (output port) para persistir el agregado ChatEscalationAssignment.
 */
public interface ChatEscalationAssignmentRepository {

    ChatEscalationAssignment save(ChatEscalationAssignment chatEscalationAssignment);

    Optional<ChatEscalationAssignment> findById(ChatEscalationAssignmentId id);

    List<ChatEscalationAssignment> findAll();

    void delete(ChatEscalationAssignment chatEscalationAssignment);
}
