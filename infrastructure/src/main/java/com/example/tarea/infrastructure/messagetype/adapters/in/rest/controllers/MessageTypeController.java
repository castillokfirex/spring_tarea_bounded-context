package com.example.tarea.infrastructure.messagetype.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.messagetype.command.RegisterMessageTypeCommand;
import com.example.tarea.application.messagetype.command.UpdateMessageTypeCommand;
import com.example.tarea.application.messagetype.dto.MessageTypeResponse;
import com.example.tarea.application.messagetype.usecase.DeleteMessageTypeUseCase;
import com.example.tarea.application.messagetype.usecase.GetMessageTypeByIdUseCase;
import com.example.tarea.application.messagetype.usecase.ListMessageTypeUseCase;
import com.example.tarea.application.messagetype.usecase.RegisterMessageTypeUseCase;
import com.example.tarea.application.messagetype.usecase.UpdateMessageTypeUseCase;
import com.example.tarea.domain.messagetype.model.valueobject.MessageTypeId;
import com.example.tarea.infrastructure.messagetype.adapters.in.rest.dtos.CreateMessageTypeRequest;
import com.example.tarea.infrastructure.messagetype.adapters.in.rest.dtos.UpdateMessageTypeRequest;
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
@RequestMapping("/api/message-types")
public class MessageTypeController {

    private final RegisterMessageTypeUseCase registerUseCase;
    private final GetMessageTypeByIdUseCase getByIdUseCase;
    private final ListMessageTypeUseCase listUseCase;
    private final UpdateMessageTypeUseCase updateUseCase;
    private final DeleteMessageTypeUseCase deleteUseCase;

    public MessageTypeController(
            RegisterMessageTypeUseCase registerUseCase,
            GetMessageTypeByIdUseCase getByIdUseCase,
            ListMessageTypeUseCase listUseCase,
            UpdateMessageTypeUseCase updateUseCase,
            DeleteMessageTypeUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<MessageTypeResponse> create(@Valid @RequestBody CreateMessageTypeRequest request) {
        var command = new RegisterMessageTypeCommand(
                request.nameType());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<MessageTypeResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageTypeResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new MessageTypeId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageTypeResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateMessageTypeRequest request) {
        var command = new UpdateMessageTypeCommand(
                new MessageTypeId(id),
                request.nameType());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new MessageTypeId(id));
        return ResponseEntity.noContent().build();
    }
}
