package com.example.tarea.domain.chatparticipant.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.example.tarea.domain.chatparticipant.model.valueobject.ChatParticipantId;

/**
 * Puerto de salida (output port) para persistir el agregado ChatParticipant.
 */
public interface ChatParticipantRepository {

    ChatParticipant save(ChatParticipant chatParticipant);

    Optional<ChatParticipant> findById(ChatParticipantId id);

    List<ChatParticipant> findAll();

    void delete(ChatParticipant chatParticipant);
}
