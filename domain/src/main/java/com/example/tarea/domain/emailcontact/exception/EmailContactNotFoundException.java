package com.example.tarea.domain.emailcontact.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.emailcontact.model.valueobject.EmailContactId;

public class EmailContactNotFoundException extends ResourceNotFoundException {

    public EmailContactNotFoundException(EmailContactId id) {
        super("EmailContact with id '" + id.value() + "' was not found");
    }
}
