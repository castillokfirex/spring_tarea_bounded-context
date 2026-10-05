package com.example.tarea.application.chatparticipant.usecase;

import com.example.tarea.application.chatparticipant.command.UpdateChatParticipantCommand;
import com.example.tarea.application.chatparticipant.dto.ChatParticipantResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatparticipant.exception.ChatParticipantNotFoundException;
import com.example.tarea.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.example.tarea.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class UpdateChatParticipantUseCase {

    private final ChatParticipantRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateChatParticipantUseCase(ChatParticipantRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatParticipantResponse execute(UpdateChatParticipantCommand command) {

        ChatParticipant chatParticipant = repository.findById(command.id())
                .orElseThrow(() -> new ChatParticipantNotFoundException(command.id()));

        chatParticipant.update(
                command.conversationId(),
                command.participantTypeId(),
                command.patientId(),
                command.professionalId());

        ChatParticipant saved = repository.save(chatParticipant);

        eventPublisher.publish(chatParticipant.domainEvents());
        chatParticipant.clearDomainEvents();

        return ChatParticipantResponse.fromDomain(saved);
    }
}
