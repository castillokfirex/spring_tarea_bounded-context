package com.example.tarea.application.chatparticipant.usecase;

import com.example.tarea.application.chatparticipant.command.RegisterChatParticipantCommand;
import com.example.tarea.application.chatparticipant.dto.ChatParticipantResponse;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.chatparticipant.model.aggregate.ChatParticipant;
import com.example.tarea.domain.chatparticipant.port.repository.ChatParticipantRepository;

public class RegisterChatParticipantUseCase {

    private final ChatParticipantRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterChatParticipantUseCase(ChatParticipantRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ChatParticipantResponse execute(RegisterChatParticipantCommand command) {

        ChatParticipant chatParticipant = ChatParticipant.register(
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
