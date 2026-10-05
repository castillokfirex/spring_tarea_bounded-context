package com.example.tarea.domain.chatmessage.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatmessage.model.aggregate.ChatMessage;
import com.example.tarea.domain.chatmessage.model.valueobject.ChatMessageId;

/**
 * Puerto de salida (output port) para persistir el agregado ChatMessage.
 */
public interface ChatMessageRepository {

    ChatMessage save(ChatMessage chatMessage);

    Optional<ChatMessage> findById(ChatMessageId id);

    List<ChatMessage> findAll();

    void delete(ChatMessage chatMessage);
}
