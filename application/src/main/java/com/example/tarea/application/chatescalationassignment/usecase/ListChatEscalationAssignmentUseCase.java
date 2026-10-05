package com.example.tarea.application.chatescalationassignment.usecase;

import java.util.List;

import com.example.tarea.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.example.tarea.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class ListChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository repository;

    public ListChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository repository) {
        this.repository = repository;
    }

    public List<ChatEscalationAssignmentResponse> execute() {
        return repository.findAll()
                .stream()
                .map(ChatEscalationAssignmentResponse::fromDomain)
                .toList();
    }
}
