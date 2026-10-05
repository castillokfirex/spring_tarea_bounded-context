package com.example.tarea.application.chatconversationaisetting.usecase;

import java.util.List;

import com.example.tarea.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.example.tarea.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class ListChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository repository;

    public ListChatConversationAiSettingUseCase(ChatConversationAiSettingRepository repository) {
        this.repository = repository;
    }

    public List<ChatConversationAiSettingResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatConversationAiSettingResponse::fromDomain)
                .toList();
    }
}
