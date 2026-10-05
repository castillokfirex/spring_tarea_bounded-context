package com.example.tarea.application.chatconversationaisetting.usecase;

import com.example.tarea.application.chatconversationaisetting.command.UpdateChatConversationAiSettingCommand;
import com.example.tarea.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundException;
import com.example.tarea.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.example.tarea.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class UpdateChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatConversationAiSettingResponse execute(UpdateChatConversationAiSettingCommand command) {

        ChatConversationAiSetting chatConversationAiSetting = repository.findById(command.id())
                .orElseThrow(() -> new ChatConversationAiSettingNotFoundException(command.id()));

        chatConversationAiSetting.update(
                command.conversationId(),
                command.aiEnabled(),
                command.defaultModelId());

        ChatConversationAiSetting saved = repository.save(chatConversationAiSetting);

        eventPublisher.publish(chatConversationAiSetting.domainEvents());
        chatConversationAiSetting.clearDomainEvents();

        return ChatConversationAiSettingResponse.fromDomain(saved);
    }
}
