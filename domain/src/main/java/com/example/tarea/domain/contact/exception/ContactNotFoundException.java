package com.example.tarea.domain.contact.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.contact.model.valueobject.ContactId;

public class ContactNotFoundException extends ResourceNotFoundException {

    public ContactNotFoundException(ContactId id) {
        super("Contact with id '" + id.value() + "' was not found");
    }
}
