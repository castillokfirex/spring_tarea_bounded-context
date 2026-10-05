package com.example.tarea.application.professionalstudy.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.professionalstudy.command.UpdateProfessionalStudyCommand;
import com.example.tarea.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.example.tarea.domain.professionalstudy.exception.ProfessionalStudyNotFoundException;
import com.example.tarea.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.example.tarea.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class UpdateProfessionalStudyUseCase {

    private final ProfessionalStudyRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateProfessionalStudyUseCase(ProfessionalStudyRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public ProfessionalStudyResponse execute(UpdateProfessionalStudyCommand command) {

        ProfessionalStudy professionalStudy = repository.findById(command.id())
                .orElseThrow(() -> new ProfessionalStudyNotFoundException(command.id()));

        professionalStudy.update(
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
