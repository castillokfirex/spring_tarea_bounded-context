package com.example.tarea.infrastructure.contact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.contact.model.aggregate.Contact;
import com.example.tarea.domain.contact.model.valueobject.ContactId;
import com.example.tarea.domain.contact.port.repository.ContactRepository;
import com.example.tarea.infrastructure.contact.adapters.out.persistence.entity.ContactJpaEntity;
import com.example.tarea.infrastructure.contact.adapters.out.persistence.mappers.ContactPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto ContactRepository con Spring Data JPA.
 */
public class ContactRepositoryAdapter implements ContactRepository {

    private final ContactJpaRepository jpaRepository;
    private final ContactPersistenceMapper mapper;

    public ContactRepositoryAdapter(ContactJpaRepository jpaRepository, ContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Contact save(Contact contact) {
        ContactJpaEntity saved = jpaRepository.save(mapper.toJpa(contact));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Contact> findById(ContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<Contact> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Contact contact) {
        jpaRepository.deleteById(contact.id().value());
    }
}
