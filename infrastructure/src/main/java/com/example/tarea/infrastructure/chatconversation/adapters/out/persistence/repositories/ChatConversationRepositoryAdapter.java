package com.example.tarea.infrastructure.chatconversation.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatconversation.model.aggregate.ChatConversation;
import com.example.tarea.domain.chatconversation.model.valueobject.ChatConversationId;
import com.example.tarea.domain.chatconversation.port.repository.ChatConversationRepository;
import com.example.tarea.infrastructure.chatconversation.adapters.out.persistence.entity.ChatConversationJpaEntity;
import com.example.tarea.infrastructure.chatconversation.adapters.out.persistence.mappers.ChatConversationPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ChatConversationRepository con Spring Data JPA.
 */
public class ChatConversationRepositoryAdapter implements ChatConversationRepository {

    private final ChatConversationJpaRepository jpaRepository;
    private final ChatConversationPersistenceMapper mapper;

    public ChatConversationRepositoryAdapter(ChatConversationJpaRepository jpaRepository, ChatConversationPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatConversation save(ChatConversation chatConversation) {
        ChatConversationJpaEntity saved = jpaRepository.save(mapper.toJpa(chatConversation));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ChatConversation> findById(ChatConversationId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatConversation> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ChatConversation chatConversation) {
        jpaRepository.deleteById(chatConversation.id().value());
    }
}
