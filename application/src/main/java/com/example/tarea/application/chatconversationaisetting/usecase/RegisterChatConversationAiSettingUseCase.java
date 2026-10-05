package com.example.tarea.application.chatconversationaisetting.usecase;

import com.example.tarea.application.chatconversationaisetting.command.RegisterChatConversationAiSettingCommand;
import com.example.tarea.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.example.tarea.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class RegisterChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatConversationAiSettingResponse execute(RegisterChatConversationAiSettingCommand command) {

        ChatConversationAiSetting chatConversationAiSetting = ChatConversationAiSetting.register(
                command.conversationId(),
                command.aiEnabled(),
                command.defaultModelId());

        ChatConversationAiSetting saved = repository.save(chatConversationAiSetting);

        eventPublisher.publish(chatConversationAiSetting.domainEvents());
        chatConversationAiSetting.clearDomainEvents();

        return ChatConversationAiSettingResponse.fromDomain(saved);
    }
}
