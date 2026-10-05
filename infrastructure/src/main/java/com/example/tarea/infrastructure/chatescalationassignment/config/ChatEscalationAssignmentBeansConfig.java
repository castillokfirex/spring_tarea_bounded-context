package com.example.tarea.infrastructure.chatescalationassignment.config;

import com.example.tarea.application.chatescalationassignment.usecase.DeleteChatEscalationAssignmentUseCase;
import com.example.tarea.application.chatescalationassignment.usecase.GetChatEscalationAssignmentByIdUseCase;
import com.example.tarea.application.chatescalationassignment.usecase.ListChatEscalationAssignmentUseCase;
import com.example.tarea.application.chatescalationassignment.usecase.RegisterChatEscalationAssignmentUseCase;
import com.example.tarea.application.chatescalationassignment.usecase.UpdateChatEscalationAssignmentUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;
import com.example.tarea.infrastructure.chatescalationassignment.adapters.out.persistence.mappers.ChatEscalationAssignmentPersistenceMapper;
import com.example.tarea.infrastructure.chatescalationassignment.adapters.out.persistence.repositories.ChatEscalationAssignmentJpaRepository;
import com.example.tarea.infrastructure.chatescalationassignment.adapters.out.persistence.repositories.ChatEscalationAssignmentRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context chatescalationassignment: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ChatEscalationAssignmentBeansConfig {

    @Bean
    public ChatEscalationAssignmentPersistenceMapper chatEscalationAssignmentPersistenceMapper() {
        return new ChatEscalationAssignmentPersistenceMapper();
    }

    @Bean
    public ChatEscalationAssignmentRepository chatEscalationAssignmentRepository(ChatEscalationAssignmentJpaRepository jpaRepository, ChatEscalationAssignmentPersistenceMapper mapper) {
        return new ChatEscalationAssignmentRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterChatEscalationAssignmentUseCase registerChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterChatEscalationAssignmentUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateChatEscalationAssignmentUseCase updateChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateChatEscalationAssignmentUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteChatEscalationAssignmentUseCase deleteChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteChatEscalationAssignmentUseCase(repository, eventPublisher);
    }

    @Bean
    public GetChatEscalationAssignmentByIdUseCase getChatEscalationAssignmentByIdUseCase(ChatEscalationAssignmentRepository repository) {
        return new GetChatEscalationAssignmentByIdUseCase(repository);
    }

    @Bean
    public ListChatEscalationAssignmentUseCase listChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) {
        return new ListChatEscalationAssignmentUseCase(repository);
    }
}
