package com.example.tarea.infrastructure.chatconversation.config;

import com.example.tarea.application.chatconversation.usecase.DeleteChatConversationUseCase;
import com.example.tarea.application.chatconversation.usecase.GetChatConversationByIdUseCase;
import com.example.tarea.application.chatconversation.usecase.ListChatConversationUseCase;
import com.example.tarea.application.chatconversation.usecase.RegisterChatConversationUseCase;
import com.example.tarea.application.chatconversation.usecase.UpdateChatConversationUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatconversation.port.repository.ChatConversationRepository;
import com.example.tarea.infrastructure.chatconversation.adapters.out.persistence.mappers.ChatConversationPersistenceMapper;
import com.example.tarea.infrastructure.chatconversation.adapters.out.persistence.repositories.ChatConversationJpaRepository;
import com.example.tarea.infrastructure.chatconversation.adapters.out.persistence.repositories.ChatConversationRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context chatconversation: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ChatConversationBeansConfig {

    @Bean
    public ChatConversationPersistenceMapper chatConversationPersistenceMapper() {
        return new ChatConversationPersistenceMapper();
    }

    @Bean
    public ChatConversationRepository chatConversationRepository(ChatConversationJpaRepository jpaRepository, ChatConversationPersistenceMapper mapper) {
        return new ChatConversationRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterChatConversationUseCase registerChatConversationUseCase(ChatConversationRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterChatConversationUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateChatConversationUseCase updateChatConversationUseCase(ChatConversationRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateChatConversationUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteChatConversationUseCase deleteChatConversationUseCase(ChatConversationRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteChatConversationUseCase(repository, eventPublisher);
    }

    @Bean
    public GetChatConversationByIdUseCase getChatConversationByIdUseCase(ChatConversationRepository repository) {
        return new GetChatConversationByIdUseCase(repository);
    }

    @Bean
    public ListChatConversationUseCase listChatConversationUseCase(ChatConversationRepository repository) {
        return new ListChatConversationUseCase(repository);
    }
}
