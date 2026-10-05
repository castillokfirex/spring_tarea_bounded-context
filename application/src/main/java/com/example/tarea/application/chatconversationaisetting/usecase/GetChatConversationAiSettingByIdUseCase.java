package com.example.tarea.application.chatconversationaisetting.usecase;

import com.example.tarea.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.example.tarea.domain.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundException;
import com.example.tarea.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.example.tarea.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class GetChatConversationAiSettingByIdUseCase {

    private final ChatConversationAiSettingRepository repository;

    public GetChatConversationAiSettingByIdUseCase(ChatConversationAiSettingRepository repository) {
        this.repository = repository;
    }

    public ChatConversationAiSettingResponse execute(ChatConversationAiSettingId id) {
        return repository.findById(id)
                .map(ChatConversationAiSettingResponse::fromDomain)
                .orElseThrow(() -> new ChatConversationAiSettingNotFoundException(id));
    }
}
