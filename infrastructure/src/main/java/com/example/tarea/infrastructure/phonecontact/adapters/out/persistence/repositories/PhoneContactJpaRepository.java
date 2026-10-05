package com.example.tarea.infrastructure.phonecontact.adapters.out.persistence.repositories;

import java.util.UUID;

import com.example.tarea.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneContactJpaRepository extends JpaRepository<PhoneContactJpaEntity, UUID> {
}
