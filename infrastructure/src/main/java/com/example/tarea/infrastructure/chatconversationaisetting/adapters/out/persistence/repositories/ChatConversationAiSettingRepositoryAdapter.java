package com.example.tarea.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.example.tarea.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.example.tarea.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;
import com.example.tarea.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;
import com.example.tarea.infrastructure.chatconversationaisetting.adapters.out.persistence.mappers.ChatConversationAiSettingPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ChatConversationAiSettingRepository con Spring Data JPA.
 */
public class ChatConversationAiSettingRepositoryAdapter implements ChatConversationAiSettingRepository {

    private final ChatConversationAiSettingJpaRepository jpaRepository;
    private final ChatConversationAiSettingPersistenceMapper mapper;

    public ChatConversationAiSettingRepositoryAdapter(ChatConversationAiSettingJpaRepository jpaRepository, ChatConversationAiSettingPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatConversationAiSetting save(ChatConversationAiSetting chatConversationAiSetting) {
        ChatConversationAiSettingJpaEntity saved = jpaRepository.save(mapper.toJpa(chatConversationAiSetting));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ChatConversationAiSetting> findById(ChatConversationAiSettingId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatConversationAiSetting> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ChatConversationAiSetting chatConversationAiSetting) {
        jpaRepository.deleteById(chatConversationAiSetting.id().value());
    }
}
