package com.example.tarea.infrastructure.chatparticipant.config;

import com.example.tarea.application.chatparticipant.usecase.DeleteChatParticipantUseCase;
import com.example.tarea.application.chatparticipant.usecase.GetChatParticipantByIdUseCase;
import com.example.tarea.application.chatparticipant.usecase.ListChatParticipantUseCase;
import com.example.tarea.application.chatparticipant.usecase.RegisterChatParticipantUseCase;
import com.example.tarea.application.chatparticipant.usecase.UpdateChatParticipantUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatparticipant.port.repository.ChatParticipantRepository;
import com.example.tarea.infrastructure.chatparticipant.adapters.out.persistence.mappers.ChatParticipantPersistenceMapper;
import com.example.tarea.infrastructure.chatparticipant.adapters.out.persistence.repositories.ChatParticipantJpaRepository;
import com.example.tarea.infrastructure.chatparticipant.adapters.out.persistence.repositories.ChatParticipantRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context chatparticipant: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ChatParticipantBeansConfig {

    @Bean
    public ChatParticipantPersistenceMapper chatParticipantPersistenceMapper() {
        return new ChatParticipantPersistenceMapper();
    }

    @Bean
    public ChatParticipantRepository chatParticipantRepository(ChatParticipantJpaRepository jpaRepository, ChatParticipantPersistenceMapper mapper) {
        return new ChatParticipantRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterChatParticipantUseCase registerChatParticipantUseCase(ChatParticipantRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterChatParticipantUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateChatParticipantUseCase updateChatParticipantUseCase(ChatParticipantRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateChatParticipantUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteChatParticipantUseCase deleteChatParticipantUseCase(ChatParticipantRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteChatParticipantUseCase(repository, eventPublisher);
    }

    @Bean
    public GetChatParticipantByIdUseCase getChatParticipantByIdUseCase(ChatParticipantRepository repository) {
        return new GetChatParticipantByIdUseCase(repository);
    }

    @Bean
    public ListChatParticipantUseCase listChatParticipantUseCase(ChatParticipantRepository repository) {
        return new ListChatParticipantUseCase(repository);
    }
}
