package com.example.tarea.application.professionalstudy.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.professionalstudy.command.RegisterProfessionalStudyCommand;
import com.example.tarea.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.example.tarea.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.example.tarea.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class RegisterProfessionalStudyUseCase {

    private final ProfessionalStudyRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterProfessionalStudyUseCase(ProfessionalStudyRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ProfessionalStudyResponse execute(RegisterProfessionalStudyCommand command) {

        ProfessionalStudy professionalStudy = ProfessionalStudy.register(
                command.studyId(),
                command.professionalId(),
                command.title(),
                command.university(),
                command.isValid(),
                command.resolutionNumber(),
                command.countryId());

        ProfessionalStudy saved = repository.save(professionalStudy);

        eventPublisher.publish(professionalStudy.domainEvents());
        professionalStudy.clearDomainEvents();

        return ProfessionalStudyResponse.fromDomain(saved);
    }
}
