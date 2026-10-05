package com.example.tarea.infrastructure.chatmessage.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatmessage.model.aggregate.ChatMessage;
import com.example.tarea.domain.chatmessage.model.valueobject.ChatMessageId;
import com.example.tarea.domain.chatmessage.port.repository.ChatMessageRepository;
import com.example.tarea.infrastructure.chatmessage.adapters.out.persistence.entity.ChatMessageJpaEntity;
import com.example.tarea.infrastructure.chatmessage.adapters.out.persistence.mappers.ChatMessagePersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ChatMessageRepository con Spring Data JPA.
 */
public class ChatMessageRepositoryAdapter implements ChatMessageRepository {

    private final ChatMessageJpaRepository jpaRepository;
    private final ChatMessagePersistenceMapper mapper;

    public ChatMessageRepositoryAdapter(ChatMessageJpaRepository jpaRepository, ChatMessagePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        ChatMessageJpaEntity saved = jpaRepository.save(mapper.toJpa(chatMessage));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ChatMessage> findById(ChatMessageId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatMessage> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ChatMessage chatMessage) {
        jpaRepository.deleteById(chatMessage.id().value());
    }
}
