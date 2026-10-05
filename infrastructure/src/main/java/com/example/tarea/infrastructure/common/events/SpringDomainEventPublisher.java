package com.example.tarea.infrastructure.common.events;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.common.event.DomainEvent;

/**
 * Adaptador que publica los eventos de dominio usando el bus de eventos de Spring.
 */
public class SpringDomainEventPublisher implements DomainEventPublisher {

    private final ApplicationEventPublisher publisher;

    public SpringDomainEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        for (DomainEvent event : events) {
            publisher.publishEvent(event);
        }
    }
}
