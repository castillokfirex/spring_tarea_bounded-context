package com.example.tarea.infrastructure.chatparticipant.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.chatparticipant.command.RegisterChatParticipantCommand;
import com.example.tarea.application.chatparticipant.command.UpdateChatParticipantCommand;
import com.example.tarea.application.chatparticipant.dto.ChatParticipantResponse;
import com.example.tarea.application.chatparticipant.usecase.DeleteChatParticipantUseCase;
import com.example.tarea.application.chatparticipant.usecase.GetChatParticipantByIdUseCase;
import com.example.tarea.application.chatparticipant.usecase.ListChatParticipantUseCase;
import com.example.tarea.application.chatparticipant.usecase.RegisterChatParticipantUseCase;
import com.example.tarea.application.chatparticipant.usecase.UpdateChatParticipantUseCase;
import com.example.tarea.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.example.tarea.infrastructure.chatparticipant.adapters.in.rest.dtos.CreateChatParticipantRequest;
import com.example.tarea.infrastructure.chatparticipant.adapters.in.rest.dtos.UpdateChatParticipantRequest;
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
@RequestMapping("/api/chat-participants")
public class ChatParticipantController {

    private final RegisterChatParticipantUseCase registerUseCase;
    private final GetChatParticipantByIdUseCase getByIdUseCase;
    private final ListChatParticipantUseCase listUseCase;
    private final UpdateChatParticipantUseCase updateUseCase;
    private final DeleteChatParticipantUseCase deleteUseCase;

    public ChatParticipantController(
            RegisterChatParticipantUseCase registerUseCase,
            GetChatParticipantByIdUseCase getByIdUseCase,
            ListChatParticipantUseCase listUseCase,
            UpdateChatParticipantUseCase updateUseCase,
            DeleteChatParticipantUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatParticipantResponse> create(@Valid @RequestBody CreateChatParticipantRequest request) {
        var command = new RegisterChatParticipantCommand(
                request.conversationId(),
                request.participantTypeId(),
                request.patientId(),
                request.professionalId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatParticipantResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatParticipantResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatParticipantId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatParticipantResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateChatParticipantRequest request) {
        var command = new UpdateChatParticipantCommand(
                new ChatParticipantId(id),
                request.conversationId(),
                request.participantTypeId(),
                request.patientId(),
                request.professionalId());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new ChatParticipantId(id));
        return ResponseEntity.noContent().build();
    }
}
