package com.example.tarea.domain.emailcontact.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.emailcontact.model.aggregate.EmailContact;
import com.example.tarea.domain.emailcontact.model.valueobject.EmailContactId;

/**
 * Puerto de salida (output port) para persistir el agregado EmailContact.
 */
public interface EmailContactRepository {

    EmailContact save(EmailContact emailContact);

    Optional<EmailContact> findById(EmailContactId id);

    List<EmailContact> findAll();

    void delete(EmailContact emailContact);

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, EmailContactId id);
}
