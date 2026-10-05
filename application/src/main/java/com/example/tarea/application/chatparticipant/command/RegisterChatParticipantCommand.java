package com.example.tarea.application.chatparticipant.command;

import java.util.UUID;

public record RegisterChatParticipantCommand(
        UUID conversationId,
        UUID participantTypeId,
        UUID patientId,
        UUID professionalId
) {
}
