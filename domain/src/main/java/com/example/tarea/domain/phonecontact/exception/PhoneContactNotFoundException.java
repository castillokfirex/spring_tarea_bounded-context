package com.example.tarea.domain.phonecontact.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.phonecontact.model.valueobject.PhoneContactId;

public class PhoneContactNotFoundException extends ResourceNotFoundException {

    public PhoneContactNotFoundException(PhoneContactId id) {
        super("PhoneContact with id '" + id.value() + "' was not found");
    }
}
