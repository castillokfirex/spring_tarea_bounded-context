package com.example.tarea.domain.chatescalationassignment.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

public class ChatEscalationAssignmentNotFoundException extends ResourceNotFoundException {

    public ChatEscalationAssignmentNotFoundException(ChatEscalationAssignmentId id) {
        super("ChatEscalationAssignment with id '" + id.value() + "' was not found");
    }
}
