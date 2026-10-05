package com.example.tarea.domain.phonecontact.port.repository;

import java.util.List;
import java.util.Optional;

import com.example.tarea.domain.phonecontact.model.aggregate.PhoneContact;
import com.example.tarea.domain.phonecontact.model.valueobject.PhoneContactId;

/**
 * Puerto de salida (output port) para persistir el agregado PhoneContact.
 */
public interface PhoneContactRepository {

    PhoneContact save(PhoneContact phoneContact);

    Optional<PhoneContact> findById(PhoneContactId id);

    List<PhoneContact> findAll();

    void delete(PhoneContact phoneContact);
}
