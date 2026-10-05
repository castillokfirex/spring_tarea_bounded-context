package com.example.tarea.infrastructure.phonecontact.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.phonecontact.model.aggregate.PhoneContact;
import com.example.tarea.domain.phonecontact.model.valueobject.PhoneContactId;
import com.example.tarea.domain.phonecontact.port.repository.PhoneContactRepository;
import com.example.tarea.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;
import com.example.tarea.infrastructure.phonecontact.adapters.out.persistence.mappers.PhoneContactPersistenceMapper;

/**
 * Adaptador de salida que implementa el puerto PhoneContactRepository con Spring Data JPA.
 */
public class PhoneContactRepositoryAdapter implements PhoneContactRepository {

    private final PhoneContactJpaRepository jpaRepository;
    private final PhoneContactPersistenceMapper mapper;

    public PhoneContactRepositoryAdapter(PhoneContactJpaRepository jpaRepository, PhoneContactPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public PhoneContact save(PhoneContact phoneContact) {
        PhoneContactJpaEntity saved = jpaRepository.save(mapper.toJpa(phoneContact));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<PhoneContact> findById(PhoneContactId id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    public List<PhoneContact> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public void delete(PhoneContact phoneContact) {
        jpaRepository.deleteById(phoneContact.id().value());
    }
}
