package com.example.tarea.application.common.port;

import java.util.List;

import com.example.tarea.domain.common.event.DomainEvent;

/**
 * Puerto de salida para publicar los eventos de dominio generados por los agregados.
 */
public interface DomainEventPublisher {

    void publish(List<DomainEvent> events);
}
