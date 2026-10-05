package com.example.tarea.application.sendertype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.sendertype.command.RegisterSenderTypeCommand;
import com.example.tarea.application.sendertype.dto.SenderTypeResponse;
import com.example.tarea.domain.sendertype.model.aggregate.SenderType;
import com.example.tarea.domain.sendertype.port.repository.SenderTypeRepository;

public class RegisterSenderTypeUseCase {

    private final SenderTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterSenderTypeUseCase(SenderTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public SenderTypeResponse execute(RegisterSenderTypeCommand command) {

        SenderType senderType = SenderType.register(
                command.nameType());

        SenderType saved = repository.save(senderType);

        eventPublisher.publish(senderType.domainEvents());
        senderType.clearDomainEvents();

        return SenderTypeResponse.fromDomain(saved);
    }
}
