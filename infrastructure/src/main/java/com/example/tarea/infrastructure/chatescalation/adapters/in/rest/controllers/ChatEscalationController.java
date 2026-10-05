package com.example.tarea.infrastructure.chatescalation.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.chatescalation.command.RegisterChatEscalationCommand;
import com.example.tarea.application.chatescalation.command.UpdateChatEscalationCommand;
import com.example.tarea.application.chatescalation.dto.ChatEscalationResponse;
import com.example.tarea.application.chatescalation.usecase.DeleteChatEscalationUseCase;
import com.example.tarea.application.chatescalation.usecase.GetChatEscalationByIdUseCase;
import com.example.tarea.application.chatescalation.usecase.ListChatEscalationUseCase;
import com.example.tarea.application.chatescalation.usecase.RegisterChatEscalationUseCase;
import com.example.tarea.application.chatescalation.usecase.UpdateChatEscalationUseCase;
import com.example.tarea.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.example.tarea.infrastructure.chatescalation.adapters.in.rest.dtos.CreateChatEscalationRequest;
import com.example.tarea.infrastructure.chatescalation.adapters.in.rest.dtos.UpdateChatEscalationRequest;
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
@RequestMapping("/api/chat-escalations")
public class ChatEscalationController {

    private final RegisterChatEscalationUseCase registerUseCase;
    private final GetChatEscalationByIdUseCase getByIdUseCase;
    private final ListChatEscalationUseCase listUseCase;
    private final UpdateChatEscalationUseCase updateUseCase;
    private final DeleteChatEscalationUseCase deleteUseCase;

    public ChatEscalationController(
            RegisterChatEscalationUseCase registerUseCase,
            GetChatEscalationByIdUseCase getByIdUseCase,
            ListChatEscalationUseCase listUseCase,
            UpdateChatEscalationUseCase updateUseCase,
            DeleteChatEscalationUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatEscalationResponse> create(@Valid @RequestBody CreateChatEscalationRequest request) {
        var command = new RegisterChatEscalationCommand(
                request.conversationId(),
                request.statusId(),
                request.fromAi(),
                request.reason());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatEscalationResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatEscalationResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatEscalationId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatEscalationResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateChatEscalationRequest request) {
        var command = new UpdateChatEscalationCommand(
                new ChatEscalationId(id),
                request.conversationId(),
                request.statusId(),
                request.fromAi(),
                request.reason());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new ChatEscalationId(id));
        return ResponseEntity.noContent().build();
    }
}
