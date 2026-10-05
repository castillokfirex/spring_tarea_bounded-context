package com.example.tarea.application.professional.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.professional.exception.ProfessionalNotFoundException;
import com.example.tarea.domain.professional.model.aggregate.Professional;
import com.example.tarea.domain.professional.model.valueobject.ProfessionalId;
import com.example.tarea.domain.professional.port.repository.ProfessionalRepository;

public class DeleteProfessionalUseCase {

    private final ProfessionalRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteProfessionalUseCase(ProfessionalRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ProfessionalId id) {

        Professional professional = repository.findById(id)
                .orElseThrow(() -> new ProfessionalNotFoundException(id));

        professional.delete();
        repository.delete(professional);

        eventPublisher.publish(professional.domainEvents());
        professional.clearDomainEvents();
    }
}
