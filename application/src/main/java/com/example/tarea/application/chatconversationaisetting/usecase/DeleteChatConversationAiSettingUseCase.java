package com.example.tarea.application.chatconversationaisetting.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundException;
import com.example.tarea.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.example.tarea.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.example.tarea.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class DeleteChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ChatConversationAiSettingId id) {

        ChatConversationAiSetting chatConversationAiSetting = repository.findById(id)
                .orElseThrow(() -> new ChatConversationAiSettingNotFoundException(id));

        chatConversationAiSetting.delete();
        repository.delete(chatConversationAiSetting);

        eventPublisher.publish(chatConversationAiSetting.domainEvents());
        chatConversationAiSetting.clearDomainEvents();
    }
}
