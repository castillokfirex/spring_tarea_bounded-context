package com.example.tarea.infrastructure.airunstatus.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.airunstatus.command.RegisterAiRunStatusCommand;
import com.example.tarea.application.airunstatus.command.UpdateAiRunStatusCommand;
import com.example.tarea.application.airunstatus.dto.AiRunStatusResponse;
import com.example.tarea.application.airunstatus.usecase.DeleteAiRunStatusUseCase;
import com.example.tarea.application.airunstatus.usecase.GetAiRunStatusByIdUseCase;
import com.example.tarea.application.airunstatus.usecase.ListAiRunStatusUseCase;
import com.example.tarea.application.airunstatus.usecase.RegisterAiRunStatusUseCase;
import com.example.tarea.application.airunstatus.usecase.UpdateAiRunStatusUseCase;
import com.example.tarea.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.example.tarea.infrastructure.airunstatus.adapters.in.rest.dtos.CreateAiRunStatusRequest;
import com.example.tarea.infrastructure.airunstatus.adapters.in.rest.dtos.UpdateAiRunStatusRequest;
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
@RequestMapping("/api/ai-runs-statuses")
public class AiRunStatusController {

    private final RegisterAiRunStatusUseCase registerUseCase;
    private final GetAiRunStatusByIdUseCase getByIdUseCase;
    private final ListAiRunStatusUseCase listUseCase;
    private final UpdateAiRunStatusUseCase updateUseCase;
    private final DeleteAiRunStatusUseCase deleteUseCase;

    public AiRunStatusController(
            RegisterAiRunStatusUseCase registerUseCase,
            GetAiRunStatusByIdUseCase getByIdUseCase,
            ListAiRunStatusUseCase listUseCase,
            UpdateAiRunStatusUseCase updateUseCase,
            DeleteAiRunStatusUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<AiRunStatusResponse> create(@Valid @RequestBody CreateAiRunStatusRequest request) {
        var command = new RegisterAiRunStatusCommand(
                request.nameStatus());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<AiRunStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiRunStatusResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new AiRunStatusId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AiRunStatusResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateAiRunStatusRequest request) {
        var command = new UpdateAiRunStatusCommand(
                new AiRunStatusId(id),
                request.nameStatus());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new AiRunStatusId(id));
        return ResponseEntity.noContent().build();
    }
}
