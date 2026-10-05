package com.example.tarea.application.patientallergy.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.patientallergy.command.RegisterPatientAllergyCommand;
import com.example.tarea.application.patientallergy.dto.PatientAllergyResponse;
import com.example.tarea.domain.patientallergy.model.aggregate.PatientAllergy;
import com.example.tarea.domain.patientallergy.port.repository.PatientAllergyRepository;

public class RegisterPatientAllergyUseCase {

    private final PatientAllergyRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterPatientAllergyUseCase(PatientAllergyRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public PatientAllergyResponse execute(RegisterPatientAllergyCommand command) {

        PatientAllergy patientAllergy = PatientAllergy.register(
                command.patientId(),
                command.substance(),
                command.reaction(),
                command.severity(),
                command.active(),
                command.recordedAt(),
                command.recordedBy());

        PatientAllergy saved = repository.save(patientAllergy);

        eventPublisher.publish(patientAllergy.domainEvents());
        patientAllergy.clearDomainEvents();

        return PatientAllergyResponse.fromDomain(saved);
    }
}
