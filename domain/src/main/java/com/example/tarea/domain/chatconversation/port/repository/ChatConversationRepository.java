package com.example.tarea.domain.chatconversation.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatconversation.model.aggregate.ChatConversation;
import com.example.tarea.domain.chatconversation.model.valueobject.ChatConversationId;

/**
 * Puerto de salida (output port) para persistir el agregado ChatConversation.
 */
public interface ChatConversationRepository {

    ChatConversation save(ChatConversation chatConversation);

    Optional<ChatConversation> findById(ChatConversationId id);

    List<ChatConversation> findAll();

    void delete(ChatConversation chatConversation);
}
