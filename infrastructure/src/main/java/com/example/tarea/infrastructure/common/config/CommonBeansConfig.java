package com.example.tarea.infrastructure.common.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.infrastructure.common.events.SpringDomainEventPublisher;

@Configuration
public class CommonBeansConfig {

    @Bean
    public DomainEventPublisher domainEventPublisher(ApplicationEventPublisher publisher) {
        return new SpringDomainEventPublisher(publisher);
    }
}
