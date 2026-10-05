package com.example.tarea.infrastructure.aimodel.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.aimodel.command.RegisterAiModelCommand;
import com.example.tarea.application.aimodel.command.UpdateAiModelCommand;
import com.example.tarea.application.aimodel.dto.AiModelResponse;
import com.example.tarea.application.aimodel.usecase.DeleteAiModelUseCase;
import com.example.tarea.application.aimodel.usecase.GetAiModelByIdUseCase;
import com.example.tarea.application.aimodel.usecase.ListAiModelUseCase;
import com.example.tarea.application.aimodel.usecase.RegisterAiModelUseCase;
import com.example.tarea.application.aimodel.usecase.UpdateAiModelUseCase;
import com.example.tarea.domain.aimodel.model.valueobject.AiModelId;
import com.example.tarea.infrastructure.aimodel.adapters.in.rest.dtos.CreateAiModelRequest;
import com.example.tarea.infrastructure.aimodel.adapters.in.rest.dtos.UpdateAiModelRequest;
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
@RequestMapping("/api/ai-models")
public class AiModelController {

    private final RegisterAiModelUseCase registerUseCase;
    private final GetAiModelByIdUseCase getByIdUseCase;
    private final ListAiModelUseCase listUseCase;
    private final UpdateAiModelUseCase updateUseCase;
    private final DeleteAiModelUseCase deleteUseCase;

    public AiModelController(
            RegisterAiModelUseCase registerUseCase,
            GetAiModelByIdUseCase getByIdUseCase,
            ListAiModelUseCase listUseCase,
            UpdateAiModelUseCase updateUseCase,
            DeleteAiModelUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<AiModelResponse> create(@Valid @RequestBody CreateAiModelRequest request) {
        var command = new RegisterAiModelCommand(
                request.providerModelId(),
                request.nameModel(),
                request.modelKey(),
                request.inputTokenPrice(),
                request.outputTokenPrice(),
                request.maxTokens(),
                request.contextWindow(),
                request.isActive());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<AiModelResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AiModelResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new AiModelId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AiModelResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateAiModelRequest request) {
        var command = new UpdateAiModelCommand(
                new AiModelId(id),
                request.providerModelId(),
                request.nameModel(),
                request.modelKey(),
                request.inputTokenPrice(),
                request.outputTokenPrice(),
                request.maxTokens(),
                request.contextWindow(),
                request.isActive());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new AiModelId(id));
        return ResponseEntity.noContent().build();
    }
}
