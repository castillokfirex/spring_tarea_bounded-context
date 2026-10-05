package com.example.tarea.application.professionalstudy.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.professionalstudy.exception.ProfessionalStudyNotFoundException;
import com.example.tarea.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.example.tarea.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.example.tarea.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class DeleteProfessionalStudyUseCase {

    private final ProfessionalStudyRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteProfessionalStudyUseCase(ProfessionalStudyRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(ProfessionalStudyId id) {

        ProfessionalStudy professionalStudy = repository.findById(id)
                .orElseThrow(() -> new ProfessionalStudyNotFoundException(id));

        professionalStudy.delete();
        repository.delete(professionalStudy);

        eventPublisher.publish(professionalStudy.domainEvents());
        professionalStudy.clearDomainEvents();
    }
}
