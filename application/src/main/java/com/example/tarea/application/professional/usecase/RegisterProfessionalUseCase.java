package com.example.tarea.application.professional.usecase;

import com.example.tarea.application.common.exception.ConflictApplicationException;
import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.professional.command.RegisterProfessionalCommand;
import com.example.tarea.application.professional.dto.ProfessionalResponse;
import com.example.tarea.domain.professional.model.aggregate.Professional;
import com.example.tarea.domain.professional.port.repository.ProfessionalRepository;

public class RegisterProfessionalUseCase {

    private final ProfessionalRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterProfessionalUseCase(ProfessionalRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ProfessionalResponse execute(RegisterProfessionalCommand command) {

        Professional professional = Professional.register(
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.lastName(),
                command.professionalType(),
                command.licenseNumber(),
                command.active(),
                command.cityId());

        if (repository.existsByDocumentNumber(professional.documentNumber())) {
            throw new ConflictApplicationException(
                    "A Professional with the same documentNumber already exists");
        }
        if (repository.existsByFirstName(professional.firstName())) {
            throw new ConflictApplicationException(
                    "A Professional with the same firstName already exists");
        }
        if (repository.existsByLastName(professional.lastName())) {
            throw new ConflictApplicationException(
                    "A Professional with the same lastName already exists");
        }
        if (repository.existsByLicenseNumber(professional.licenseNumber())) {
            throw new ConflictApplicationException(
                    "A Professional with the same licenseNumber already exists");
        }

        Professional saved = repository.save(professional);

        eventPublisher.publish(professional.domainEvents());
        professional.clearDomainEvents();

        return ProfessionalResponse.fromDomain(saved);
    }
}
