package com.example.tarea.infrastructure.chatconversationaisetting.config;

import com.example.tarea.application.chatconversationaisetting.usecase.DeleteChatConversationAiSettingUseCase;
import com.example.tarea.application.chatconversationaisetting.usecase.GetChatConversationAiSettingByIdUseCase;
import com.example.tarea.application.chatconversationaisetting.usecase.ListChatConversationAiSettingUseCase;
import com.example.tarea.application.chatconversationaisetting.usecase.RegisterChatConversationAiSettingUseCase;
import com.example.tarea.application.chatconversationaisetting.usecase.UpdateChatConversationAiSettingUseCase;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;
import com.example.tarea.infrastructure.chatconversationaisetting.adapters.out.persistence.mappers.ChatConversationAiSettingPersistenceMapper;
import com.example.tarea.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories.ChatConversationAiSettingJpaRepository;
import com.example.tarea.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories.ChatConversationAiSettingRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cableado (wiring) del bounded context chatconversationaisetting: las capas domain y application
 * no dependen de Spring, por eso sus clases se registran aqui como beans.
 */
@Configuration
public class ChatConversationAiSettingBeansConfig {

    @Bean
    public ChatConversationAiSettingPersistenceMapper chatConversationAiSettingPersistenceMapper() {
        return new ChatConversationAiSettingPersistenceMapper();
    }

    @Bean
    public ChatConversationAiSettingRepository chatConversationAiSettingRepository(ChatConversationAiSettingJpaRepository jpaRepository, ChatConversationAiSettingPersistenceMapper mapper) {
        return new ChatConversationAiSettingRepositoryAdapter(jpaRepository, mapper);
    }

    @Bean
    public RegisterChatConversationAiSettingUseCase registerChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository, DomainEventPublisher eventPublisher) {
        return new RegisterChatConversationAiSettingUseCase(repository, eventPublisher);
    }

    @Bean
    public UpdateChatConversationAiSettingUseCase updateChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository, DomainEventPublisher eventPublisher) {
        return new UpdateChatConversationAiSettingUseCase(repository, eventPublisher);
    }

    @Bean
    public DeleteChatConversationAiSettingUseCase deleteChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository, DomainEventPublisher eventPublisher) {
        return new DeleteChatConversationAiSettingUseCase(repository, eventPublisher);
    }

    @Bean
    public GetChatConversationAiSettingByIdUseCase getChatConversationAiSettingByIdUseCase(ChatConversationAiSettingRepository repository) {
        return new GetChatConversationAiSettingByIdUseCase(repository);
    }

    @Bean
    public ListChatConversationAiSettingUseCase listChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) {
        return new ListChatConversationAiSettingUseCase(repository);
    }
}
