package com.example.tarea.domain.sendertype.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.sendertype.model.aggregate.SenderType;
import com.example.tarea.domain.sendertype.model.valueobject.SenderTypeId;

/**
 * Puerto de salida (output port) para persistir el agregado SenderType.
 */
public interface SenderTypeRepository {

    SenderType save(SenderType senderType);

    Optional<SenderType> findById(SenderTypeId id);

    List<SenderType> findAll();

    void delete(SenderType senderType);
}
