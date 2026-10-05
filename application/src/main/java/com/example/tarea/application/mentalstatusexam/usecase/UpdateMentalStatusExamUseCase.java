package com.example.tarea.application.mentalstatusexam.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.mentalstatusexam.command.UpdateMentalStatusExamCommand;
import com.example.tarea.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.example.tarea.domain.mentalstatusexam.exception.MentalStatusExamNotFoundException;
import com.example.tarea.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.example.tarea.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class UpdateMentalStatusExamUseCase {

    private final MentalStatusExamRepository repository;
    private final DomainEventPublisher eventPublisher;

    public UpdateMentalStatusExamUseCase(MentalStatusExamRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public MentalStatusExamResponse execute(UpdateMentalStatusExamCommand command) {

        MentalStatusExam mentalStatusExam = repository.findById(command.id())
                .orElseThrow(() -> new MentalStatusExamNotFoundException(command.id()));

        mentalStatusExam.update(
                command.encounterId(),
                command.appearance(),
                command.behavior(),
                command.attitude(),
                command.consciousness(),
                command.orientation(),
                command.attention(),
                command.memory(),
                command.speech(),
                command.mood(),
                command.affect(),
                command.thoughtProcess(),
                command.thoughtContent(),
                command.perception(),
                command.judgment(),
                command.insight(),
                command.psychomotorActivity(),
                command.observations());

        MentalStatusExam saved = repository.save(mentalStatusExam);

        eventPublisher.publish(mentalStatusExam.domainEvents());
        mentalStatusExam.clearDomainEvents();

        return MentalStatusExamResponse.fromDomain(saved);
    }
}
