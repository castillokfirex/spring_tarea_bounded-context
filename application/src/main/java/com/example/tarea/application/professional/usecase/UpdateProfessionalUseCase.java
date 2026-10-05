package com.example.tarea.application.professional.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.professional.command.UpdateProfessionalCommand;
import com.example.tarea.application.professional.dto.ProfessionalResponse;
import com.example.tarea.domain.professional.exception.ProfessionalNotFoundException;
import com.example.tarea.domain.professional.model.aggregate.Professional;
import com.example.tarea.domain.professional.port.repository.ProfessionalRepository;

public class UpdateProfessionalUseCase {

    private final ProfessionalRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateProfessionalUseCase(ProfessionalRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ProfessionalResponse execute(UpdateProfessionalCommand command) {

        Professional professional = repository.findById(command.id())
                .orElseThrow(() -> new ProfessionalNotFoundException(command.id()));

        professional.update(
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.lastName(),
                command.professionalType(),
                command.licenseNumber(),
                command.active(),
                command.cityId());

        if (repository.existsByDocumentNumberAndIdNot(professional.documentNumber(), professional.id())) {
            throw new ConflictApplicationException(
                    "A Professional with the same documentNumber already exists");
        }
        if (repository.existsByFirstNameAndIdNot(professional.firstName(), professional.id())) {
            throw new ConflictApplicationException(
                    "A Professional with the same firstName already exists");
        }
        if (repository.existsByLastNameAndIdNot(professional.lastName(), professional.id())) {
            throw new ConflictApplicationException(
                    "A Professional with the same lastName already exists");
        }
        if (repository.existsByLicenseNumberAndIdNot(professional.licenseNumber(), professional.id())) {
            throw new ConflictApplicationException(
                    "A Professional with the same licenseNumber already exists");
        }

        Professional saved = repository.save(professional);

        eventPublisher.publish(professional.domainEvents());
        professional.clearDomainEvents();

        return ProfessionalResponse.fromDomain(saved);
    }
}
