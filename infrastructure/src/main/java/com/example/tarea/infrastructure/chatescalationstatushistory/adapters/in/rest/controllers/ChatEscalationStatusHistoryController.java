package com.example.tarea.infrastructure.chatescalationstatushistory.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.chatescalationstatushistory.command.RegisterChatEscalationStatusHistoryCommand;
import com.example.tarea.application.chatescalationstatushistory.command.UpdateChatEscalationStatusHistoryCommand;
import com.example.tarea.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import com.example.tarea.application.chatescalationstatushistory.usecase.DeleteChatEscalationStatusHistoryUseCase;
import com.example.tarea.application.chatescalationstatushistory.usecase.GetChatEscalationStatusHistoryByIdUseCase;
import com.example.tarea.application.chatescalationstatushistory.usecase.ListChatEscalationStatusHistoryUseCase;
import com.example.tarea.application.chatescalationstatushistory.usecase.RegisterChatEscalationStatusHistoryUseCase;
import com.example.tarea.application.chatescalationstatushistory.usecase.UpdateChatEscalationStatusHistoryUseCase;
import com.example.tarea.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.example.tarea.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos.CreateChatEscalationStatusHistoryRequest;
import com.example.tarea.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos.UpdateChatEscalationStatusHistoryRequest;
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
@RequestMapping("/api/chat-escalation-status-history")
public class ChatEscalationStatusHistoryController {

    private final RegisterChatEscalationStatusHistoryUseCase registerUseCase;
    private final GetChatEscalationStatusHistoryByIdUseCase getByIdUseCase;
    private final ListChatEscalationStatusHistoryUseCase listUseCase;
    private final UpdateChatEscalationStatusHistoryUseCase updateUseCase;
    private final DeleteChatEscalationStatusHistoryUseCase deleteUseCase;

    public ChatEscalationStatusHistoryController(
            RegisterChatEscalationStatusHistoryUseCase registerUseCase,
            GetChatEscalationStatusHistoryByIdUseCase getByIdUseCase,
            ListChatEscalationStatusHistoryUseCase listUseCase,
            UpdateChatEscalationStatusHistoryUseCase updateUseCase,
            DeleteChatEscalationStatusHistoryUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatEscalationStatusHistoryResponse> create(@Valid @RequestBody CreateChatEscalationStatusHistoryRequest request) {
        var command = new RegisterChatEscalationStatusHistoryCommand(
                request.escalationId(),
                request.escalationStatusId(),
                request.changedAt());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatEscalationStatusHistoryResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatEscalationStatusHistoryResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatEscalationStatusHistoryId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatEscalationStatusHistoryResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateChatEscalationStatusHistoryRequest request) {
        var command = new UpdateChatEscalationStatusHistoryCommand(
                new ChatEscalationStatusHistoryId(id),
                request.escalationId(),
                request.escalationStatusId(),
                request.changedAt());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new ChatEscalationStatusHistoryId(id));
        return ResponseEntity.noContent().build();
    }
}
