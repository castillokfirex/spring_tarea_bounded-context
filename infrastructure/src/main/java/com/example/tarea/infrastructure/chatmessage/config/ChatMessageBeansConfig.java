package com.example.tarea.infrastructure.chatmessage.config;

import com.example.tarea.application.chatmessage.usecase.DeleteChatMessageUseCase;
import com.example.tarea.application.chatmessage.usecase.GetChatMessageByIdUseCase;
import com.example.tarea.application.chatmessage.usecase.ListChatMessageUseCase;
import com.example.tarea.application.chatmessage.usecase.RegisterChatMessageUseCase;
import com.example.tarea.application.chatmessage.usecase.UpdateChatMessageUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatmessage.port.repository.ChatMessageRepository;
import com.example.tarea.infrastructure.chatmessage.adapters.out.persistence.mappers.ChatMessagePersistenceMapper;
import com.example.tarea.infrastructure.chatmessage.adapters.out.persistence.repositories.ChatMessageJpaRepository;
import com.example.tarea.infrastructure.chatmessage.adapters.out.persistence.repositories.ChatMessageRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context chatmessage: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ChatMessageBeansConfig {

    @Bean
    public ChatMessagePersistenceMapper chatMessagePersistenceMapper() {
        return new ChatMessagePersistenceMapper();
    }

    @Bean
    public ChatMessageRepository chatMessageRepository(ChatMessageJpaRepository jpaRepository, ChatMessagePersistenceMapper mapper) {
        return new ChatMessageRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterChatMessageUseCase registerChatMessageUseCase(ChatMessageRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterChatMessageUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateChatMessageUseCase updateChatMessageUseCase(ChatMessageRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateChatMessageUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteChatMessageUseCase deleteChatMessageUseCase(ChatMessageRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteChatMessageUseCase(repository, eventPublisher);
    }

    @Bean
    public GetChatMessageByIdUseCase getChatMessageByIdUseCase(ChatMessageRepository repository) {
        return new GetChatMessageByIdUseCase(repository);
    }

    @Bean
    public ListChatMessageUseCase listChatMessageUseCase(ChatMessageRepository repository) {
        return new ListChatMessageUseCase(repository);
    }
}
