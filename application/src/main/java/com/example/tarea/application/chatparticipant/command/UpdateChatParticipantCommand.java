package com.example.tarea.application.chatparticipant.command;

import java.util.UUID;

import com.example.tarea.domain.chatparticipant.model.valueobject.ChatParticipantId;

public record UpdateChatParticipantCommand(
        ChatParticipantId id,
        UUID conversationId,
        UUID participantTypeId,
        UUID patientId,
        UUID professionalId
) {
}
