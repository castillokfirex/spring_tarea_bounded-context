package com.example.tarea.infrastructure.priority.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.priority.command.RegisterPriorityCommand;
import com.example.tarea.application.priority.command.UpdatePriorityCommand;
import com.example.tarea.application.priority.dto.PriorityResponse;
import com.example.tarea.application.priority.usecase.DeletePriorityUseCase;
import com.example.tarea.application.priority.usecase.GetPriorityByIdUseCase;
import com.example.tarea.application.priority.usecase.ListPriorityUseCase;
import com.example.tarea.application.priority.usecase.RegisterPriorityUseCase;
import com.example.tarea.application.priority.usecase.UpdatePriorityUseCase;
import com.example.tarea.domain.priority.model.valueobject.PriorityId;
import com.example.tarea.infrastructure.priority.adapters.in.rest.dtos.CreatePriorityRequest;
import com.example.tarea.infrastructure.priority.adapters.in.rest.dtos.UpdatePriorityRequest;
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
@RequestMapping("/api/priorities")
public class PriorityController {

    private final RegisterPriorityUseCase registerUseCase;
    private final GetPriorityByIdUseCase getByIdUseCase;
    private final ListPriorityUseCase listUseCase;
    private final UpdatePriorityUseCase updateUseCase;
    private final DeletePriorityUseCase deleteUseCase;

    public PriorityController(
            RegisterPriorityUseCase registerUseCase,
            GetPriorityByIdUseCase getByIdUseCase,
            ListPriorityUseCase listUseCase,
            UpdatePriorityUseCase updateUseCase,
            DeletePriorityUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<PriorityResponse> create(@Valid @RequestBody CreatePriorityRequest request) {
        var command = new RegisterPriorityCommand(
                request.namePriority());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<PriorityResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriorityResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new PriorityId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriorityResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdatePriorityRequest request) {
        var command = new UpdatePriorityCommand(
                new PriorityId(id),
                request.namePriority());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new PriorityId(id));
        return ResponseEntity.noContent().build();
    }
}
