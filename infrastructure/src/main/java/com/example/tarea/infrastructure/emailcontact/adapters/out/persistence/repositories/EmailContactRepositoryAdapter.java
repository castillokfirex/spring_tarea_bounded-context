package com.example.tarea.infrastructure.emailcontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.emailcontact.model.aggregate.EmailContact;
import com.example.tarea.domain.emailcontact.model.valueobject.EmailContactId;
import com.example.tarea.domain.emailcontact.port.repository.EmailContactRepository;
import com.example.tarea.infrastructure.emailcontact.adapters.out.persistence.entity.EmailContactJpaEntity;
import com.example.tarea.infrastructure.emailcontact.adapters.out.persistence.mappers.EmailContactPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto EmailContactRepository con Spring Data JPA.
 */
public class EmailContactRepositoryAdapter implements EmailContactRepository {

    private final EmailContactJpaRepository jpaRepository;
    private final EmailContactPersistenceMapper mapper;

    public EmailContactRepositoryAdapter(EmailContactJpaRepository jpaRepository, EmailContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EmailContact save(EmailContact emailContact) {
        EmailContactJpaEntity saved = jpaRepository.save(mapper.toJpa(emailContact));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<EmailContact> findById(EmailContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<EmailContact> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(EmailContact emailContact) {
        jpaRepository.deleteById(emailContact.id().value());
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByEmailAndIdNot(String email, EmailContactId id) {
        return jpaRepository.existsByEmailAndIdNot(email, id.value());
    }
}
