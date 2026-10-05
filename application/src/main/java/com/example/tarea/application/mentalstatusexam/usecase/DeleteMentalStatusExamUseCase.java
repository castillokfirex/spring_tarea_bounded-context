package com.example.tarea.application.mentalstatusexam.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.domain.mentalstatusexam.exception.MentalStatusExamNotFoundException;
import com.example.tarea.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.example.tarea.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import com.example.tarea.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class DeleteMentalStatusExamUseCase {

    private final MentalStatusExamRepository repository;
    private final DomainEventPublisher eventPublisher;

    public DeleteMentalStatusExamUseCase(MentalStatusExamRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void execute(MentalStatusExamId id) {

        MentalStatusExam mentalStatusExam = repository.findById(id)
                .orElseThrow(() -> new MentalStatusExamNotFoundException(id));

        mentalStatusExam.delete();
        repository.delete(mentalStatusExam);

        eventPublisher.publish(mentalStatusExam.domainEvents());
        mentalStatusExam.clearDomainEvents();
    }
}
