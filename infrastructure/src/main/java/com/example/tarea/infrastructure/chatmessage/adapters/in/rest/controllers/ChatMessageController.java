package com.example.tarea.infrastructure.chatmessage.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.chatmessage.command.RegisterChatMessageCommand;
import com.example.tarea.application.chatmessage.command.UpdateChatMessageCommand;
import com.example.tarea.application.chatmessage.dto.ChatMessageResponse;
import com.example.tarea.application.chatmessage.usecase.DeleteChatMessageUseCase;
import com.example.tarea.application.chatmessage.usecase.GetChatMessageByIdUseCase;
import com.example.tarea.application.chatmessage.usecase.ListChatMessageUseCase;
import com.example.tarea.application.chatmessage.usecase.RegisterChatMessageUseCase;
import com.example.tarea.application.chatmessage.usecase.UpdateChatMessageUseCase;
import com.example.tarea.domain.chatmessage.model.valueobject.ChatMessageId;
import com.example.tarea.infrastructure.chatmessage.adapters.in.rest.dtos.CreateChatMessageRequest;
import com.example.tarea.infrastructure.chatmessage.adapters.in.rest.dtos.UpdateChatMessageRequest;
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
@RequestMapping("/api/chat-messages")
public class ChatMessageController {

    private final RegisterChatMessageUseCase registerUseCase;
    private final GetChatMessageByIdUseCase getByIdUseCase;
    private final ListChatMessageUseCase listUseCase;
    private final UpdateChatMessageUseCase updateUseCase;
    private final DeleteChatMessageUseCase deleteUseCase;

    public ChatMessageController(
            RegisterChatMessageUseCase registerUseCase,
            GetChatMessageByIdUseCase getByIdUseCase,
            ListChatMessageUseCase listUseCase,
            UpdateChatMessageUseCase updateUseCase,
            DeleteChatMessageUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatMessageResponse> create(@Valid @RequestBody CreateChatMessageRequest request) {
        var command = new RegisterChatMessageCommand(
                request.conversationId(),
                request.messageTypeId(),
                request.participantId(),
                request.content(),
                request.metadata());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatMessageResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatMessageResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatMessageId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatMessageResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateChatMessageRequest request) {
        var command = new UpdateChatMessageCommand(
                new ChatMessageId(id),
                request.conversationId(),
                request.messageTypeId(),
                request.participantId(),
                request.content(),
                request.metadata());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new ChatMessageId(id));
        return ResponseEntity.noContent().build();
    }
}
