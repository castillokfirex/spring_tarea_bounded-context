package com.example.tarea.domain.messagetype.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.messagetype.model.aggregate.MessageType;
import com.example.tarea.domain.messagetype.model.valueobject.MessageTypeId;

/**
 * Puerto de salida (output port) para persistir el agregado MessageType.
 */
public interface MessageTypeRepository {

    MessageType save(MessageType messageType);

    Optional<MessageType> findById(MessageTypeId id);

    List<MessageType> findAll();

    void delete(MessageType messageType);
}
