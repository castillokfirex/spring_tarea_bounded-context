package com.example.tarea.application.professionaltype.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.professionaltype.command.UpdateProfessionalTypeCommand;
import com.example.tarea.application.professionaltype.dto.ProfessionalTypeResponse;
import com.example.tarea.domain.professionaltype.exception.ProfessionalTypeNotFoundException;
import com.example.tarea.domain.professionaltype.model.aggregate.ProfessionalType;
import com.example.tarea.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class UpdateProfessionalTypeUseCase {

    private final ProfessionalTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateProfessionalTypeUseCase(ProfessionalTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ProfessionalTypeResponse execute(UpdateProfessionalTypeCommand command) {

        ProfessionalType professionalType = repository.findById(command.id())
                .orElseThrow(() -> new ProfessionalTypeNotFoundException(command.id()));

        professionalType.update(
                command.name());

        if (repository.existsByNameAndIdNot(professionalType.name(), professionalType.id())) {
            throw new ConflictApplicationException(
                    "A ProfessionalType with the same name already exists");
        }

        ProfessionalType saved = repository.save(professionalType);

        eventPublisher.publish(professionalType.domainEvents());
        professionalType.clearDomainEvents();

        return ProfessionalTypeResponse.fromDomain(saved);
    }
}
