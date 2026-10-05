package com.example.tarea.domain.chatparticipant.exception;

import com.example.tarea.domain.common.exception.ResourceNotFoundException;
import com.example.tarea.domain.chatparticipant.model.valueobject.ChatParticipantId;

public class ChatParticipantNotFoundException extends ResourceNotFoundException {

    public ChatParticipantNotFoundException(ChatParticipantId id) {
        super("ChatParticipant with id '" + id.value() + "' was not found");
    }
}
