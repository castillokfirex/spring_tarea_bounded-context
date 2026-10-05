package com.example.tarea.application.professionaltype.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.professionaltype.exception.ProfessionalTypeNotFoundException;
import com.example.tarea.domain.professionaltype.model.aggregate.ProfessionalType;
import com.example.tarea.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.example.tarea.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class DeleteProfessionalTypeUseCase {

    private final ProfessionalTypeRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteProfessionalTypeUseCase(ProfessionalTypeRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ProfessionalTypeId id) {

        ProfessionalType professionalType = repository.findById(id)
                .orElseThrow(() -> new ProfessionalTypeNotFoundException(id));

        professionalType.delete();
        repository.delete(professionalType);

        eventPublisher.publish(professionalType.domainEvents());
        professionalType.clearDomainEvents();
    }
}
