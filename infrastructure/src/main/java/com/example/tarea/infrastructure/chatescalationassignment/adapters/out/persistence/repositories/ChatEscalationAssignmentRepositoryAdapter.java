package com.example.tarea.infrastructure.chatescalationassignment.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.example.tarea.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.example.tarea.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;
import com.example.tarea.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;
import com.example.tarea.infrastructure.chatescalationassignment.adapters.out.persistence.mappers.ChatEscalationAssignmentPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ChatEscalationAssignmentRepository con Spring Data JPA.
 */
public class ChatEscalationAssignmentRepositoryAdapter implements ChatEscalationAssignmentRepository {

    private final ChatEscalationAssignmentJpaRepository jpaRepository;
    private final ChatEscalationAssignmentPersistenceMapper mapper;

    public ChatEscalationAssignmentRepositoryAdapter(ChatEscalationAssignmentJpaRepository jpaRepository, ChatEscalationAssignmentPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalationAssignment save(ChatEscalationAssignment chatEscalationAssignment) {
        ChatEscalationAssignmentJpaEntity saved = jpaRepository.save(mapper.toJpa(chatEscalationAssignment));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ChatEscalationAssignment> findById(ChatEscalationAssignmentId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<ChatEscalationAssignment> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(ChatEscalationAssignment chatEscalationAssignment) {
        jpaRepository.deleteById(chatEscalationAssignment.id().value());
    }
}
