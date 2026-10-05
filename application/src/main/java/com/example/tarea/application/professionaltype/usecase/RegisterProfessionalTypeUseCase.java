package com.example.tarea.application.professionaltype.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.professionaltype.command.RegisterProfessionalTypeCommand;
import com.example.tarea.application.professionaltype.dto.ProfessionalTypeResponse;
import com.example.tarea.domain.professionaltype.model.aggregate.ProfessionalType;
import com.example.tarea.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class RegisterProfessionalTypeUseCase {

    private final ProfessionalTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterProfessionalTypeUseCase(ProfessionalTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ProfessionalTypeResponse execute(RegisterProfessionalTypeCommand command) {

        ProfessionalType professionalType = ProfessionalType.register(
                command.name());

        if (repository.existsByName(professionalType.name())) {
            throw new ConflictApplicationException(
                    "A ProfessionalType with the same name already exists");
        }

        ProfessionalType saved = repository.save(professionalType);

        eventPublisher.publish(professionalType.domainEvents());
        professionalType.clearDomainEvents();

        return ProfessionalTypeResponse.fromDomain(saved);
    }
}
