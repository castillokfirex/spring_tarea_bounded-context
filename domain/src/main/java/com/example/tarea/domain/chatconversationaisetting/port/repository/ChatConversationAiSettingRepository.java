package com.example.tarea.domain.chatconversationaisetting.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.example.tarea.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;

/**
 * Puerto de salida (output port) para persistir el agregado ChatConversationAiSetting.
 */
public interface ChatConversationAiSettingRepository {

    ChatConversationAiSetting save(ChatConversationAiSetting chatConversationAiSetting);

    Optional<ChatConversationAiSetting> findById(ChatConversationAiSettingId id);

    List<ChatConversationAiSetting> findAll();

    void delete(ChatConversationAiSetting chatConversationAiSetting);
}
