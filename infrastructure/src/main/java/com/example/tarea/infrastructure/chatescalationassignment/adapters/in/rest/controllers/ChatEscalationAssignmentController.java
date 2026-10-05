package com.example.tarea.infrastructure.chatescalationassignment.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.chatescalationassignment.command.RegisterChatEscalationAssignmentCommand;
import com.example.tarea.application.chatescalationassignment.command.UpdateChatEscalationAssignmentCommand;
import com.example.tarea.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.example.tarea.application.chatescalationassignment.usecase.DeleteChatEscalationAssignmentUseCase;
import com.example.tarea.application.chatescalationassignment.usecase.GetChatEscalationAssignmentByIdUseCase;
import com.example.tarea.application.chatescalationassignment.usecase.ListChatEscalationAssignmentUseCase;
import com.example.tarea.application.chatescalationassignment.usecase.RegisterChatEscalationAssignmentUseCase;
import com.example.tarea.application.chatescalationassignment.usecase.UpdateChatEscalationAssignmentUseCase;
import com.example.tarea.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.example.tarea.infrastructure.chatescalationassignment.adapters.in.rest.dtos.CreateChatEscalationAssignmentRequest;
import com.example.tarea.infrastructure.chatescalationassignment.adapters.in.rest.dtos.UpdateChatEscalationAssignmentRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat-escalation-assignments")
public class ChatEscalationAssignmentController {

    private final RegisterChatEscalationAssignmentUseCase registerUseCase;
    private final GetChatEscalationAssignmentByIdUseCase getByIdUseCase;
    private final ListChatEscalationAssignmentUseCase listUseCase;
    private final UpdateChatEscalationAssignmentUseCase updateUseCase;
    private final DeleteChatEscalationAssignmentUseCase deleteUseCase;

    public ChatEscalationAssignmentController(
            RegisterChatEscalationAssignmentUseCase registerUseCase,
            GetChatEscalationAssignmentByIdUseCase getByIdUseCase,
            ListChatEscalationAssignmentUseCase listUseCase,
            UpdateChatEscalationAssignmentUseCase updateUseCase,
            DeleteChatEscalationAssignmentUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatEscalationAssignmentResponse> create(@Valid @RequestBody CreateChatEscalationAssignmentRequest request) {
        var command = new RegisterChatEscalationAssignmentCommand(
                request.escalationId(),
                request.professionalId(),
                request.assignedAt());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatEscalationAssignmentResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatEscalationAssignmentResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatEscalationAssignmentId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatEscalationAssignmentResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateChatEscalationAssignmentRequest request) {
        var command = new UpdateChatEscalationAssignmentCommand(
                new ChatEscalationAssignmentId(id),
                request.escalationId(),
                request.professionalId(),
                request.assignedAt());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new ChatEscalationAssignmentId(id));
        return ResponseEntity.noContent().build();
    }
}
