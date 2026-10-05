package com.example.tarea.application.mentalstatusexam.usecase;

import com.example.tarea.application.common.port.DomainEventPublisher;
import com.example.tarea.application.mentalstatusexam.command.RegisterMentalStatusExamCommand;
import com.example.tarea.application.mentalstatusexam.dto.MentalStatusExamResponse;
import com.example.tarea.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.example.tarea.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

public class RegisterMentalStatusExamUseCase {

    private final MentalStatusExamRepository repository;
    private final DomainEventPublisher eventPublisher;

    public RegisterMentalStatusExamUseCase(MentalStatusExamRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public MentalStatusExamResponse execute(RegisterMentalStatusExamCommand command) {

        MentalStatusExam mentalStatusExam = MentalStatusExam.register(
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
                command.observations(),
                command.createdBy());

        MentalStatusExam saved = repository.save(mentalStatusExam);

        eventPublisher.publish(mentalStatusExam.domainEvents());
        mentalStatusExam.clearDomainEvents();

        return MentalStatusExamResponse.fromDomain(saved);
    }
}
