package com.example.tarea.application.sendertype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.sendertype.command.UpdateSenderTypeCommand;
import com.example.tarea.application.sendertype.dto.SenderTypeResponse;
import com.example.tarea.domain.sendertype.exception.SenderTypeNotFoundException;
import com.example.tarea.domain.sendertype.model.aggregate.SenderType;
import com.example.tarea.domain.sendertype.port.repository.SenderTypeRepository;

public class UpdateSenderTypeUseCase {

    private final SenderTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateSenderTypeUseCase(SenderTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public SenderTypeResponse execute(UpdateSenderTypeCommand command) {

        SenderType senderType = repository.findById(command.id())
                .orElseThrow(() -> new SenderTypeNotFoundException(command.id()));

        senderType.update(
                command.nameType());

        SenderType saved = repository.save(senderType);

        eventPublisher.publish(senderType.domainEvents());
        senderType.clearDomainEvents();

        return SenderTypeResponse.fromDomain(saved);
    }
}
