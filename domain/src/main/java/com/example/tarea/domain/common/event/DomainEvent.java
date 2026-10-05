package com.example.tarea.domain.common.event;

import java.time.LocalDateTime;

public interface DomainEvent {

    LocalDateTime occurredOn();
}
