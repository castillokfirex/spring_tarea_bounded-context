package com.example.tarea.infrastructure.chatparticipant.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.example.tarea.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.example.tarea.domain.chatparticipant.port.repository.ChatParticipantRepository;
import com.example.tarea.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;
import com.example.tarea.infrastructure.chatparticipant.adapters.out.persistence.mappers.ChatParticipantPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ChatParticipantRepository con Spring Data JPA.
 */
public class ChatParticipantRepositoryAdapter implements ChatParticipantRepository {

    private final ChatParticipantJpaRepository jpaRepository;
    private final ChatParticipantPersistenceMapper mapper;

    public ChatParticipantRepositoryAdapter(ChatParticipantJpaRepository jpaRepository, ChatParticipantPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatParticipant save(ChatParticipant chatParticipant) {
        ChatParticipantJpaEntity saved = jpaRepository.save(mapper.toJpa(chatParticipant));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ChatParticipant> findById(ChatParticipantId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatParticipant> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ChatParticipant chatParticipant) {
        jpaRepository.deleteById(chatParticipant.id().value());
    }
}
