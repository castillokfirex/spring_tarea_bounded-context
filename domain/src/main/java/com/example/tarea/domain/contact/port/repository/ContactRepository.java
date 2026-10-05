package com.example.tarea.domain.contact.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.contact.model.aggregate.Contact;
import com.example.tarea.domain.contact.model.valueobject.ContactId;

/**
 * Puerto de salida (output port) para persistir el agregado Contact.
 */
public interface ContactRepository {

    Contact save(Contact contact);

    Optional<Contact> findById(ContactId id);

    List<Contact> findAll();

    void delete(Contact contact);
}
