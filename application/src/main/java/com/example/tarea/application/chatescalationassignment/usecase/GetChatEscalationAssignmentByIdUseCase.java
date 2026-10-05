package com.example.tarea.application.chatescalationassignment.usecase;

import com.example.tarea.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.example.tarea.domain.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundException;
import com.example.tarea.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.example.tarea.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class GetChatEscalationAssignmentByIdUseCase {

    private final ChatEscalationAssignmentRepository repository;

    public GetChatEscalationAssignmentByIdUseCase(ChatEscalationAssignmentRepository repository) {
        this.repository = repository;
    }

    public ChatEscalationAssignmentResponse execute(ChatEscalationAssignmentId id) {
        return repository.findById(id)
                .map(ChatEscalationAssignmentResponse::fromDomain)
                .orElseThrow(() -> new ChatEscalationAssignmentNotFoundException(id));
    }
}
