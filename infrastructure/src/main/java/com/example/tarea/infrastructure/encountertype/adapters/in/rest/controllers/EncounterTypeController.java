package com.example.tarea.infrastructure.encountertype.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.encountertype.command.RegisterEncounterTypeCommand;
import com.example.tarea.application.encountertype.command.UpdateEncounterTypeCommand;
import com.example.tarea.application.encountertype.dto.EncounterTypeResponse;
import com.example.tarea.application.encountertype.usecase.DeleteEncounterTypeUseCase;
import com.example.tarea.application.encountertype.usecase.GetEncounterTypeByIdUseCase;
import com.example.tarea.application.encountertype.usecase.ListEncounterTypeUseCase;
import com.example.tarea.application.encountertype.usecase.RegisterEncounterTypeUseCase;
import com.example.tarea.application.encountertype.usecase.UpdateEncounterTypeUseCase;
import com.example.tarea.domain.encountertype.model.valueobject.EncounterTypeId;
import com.example.tarea.infrastructure.encountertype.adapters.in.rest.dtos.CreateEncounterTypeRequest;
import com.example.tarea.infrastructure.encountertype.adapters.in.rest.dtos.UpdateEncounterTypeRequest;
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
@RequestMapping("/api/encounter-types")
public class EncounterTypeController {

    private final RegisterEncounterTypeUseCase registerUseCase;
    private final GetEncounterTypeByIdUseCase getByIdUseCase;
    private final ListEncounterTypeUseCase listUseCase;
    private final UpdateEncounterTypeUseCase updateUseCase;
    private final DeleteEncounterTypeUseCase deleteUseCase;

    public EncounterTypeController(
            RegisterEncounterTypeUseCase registerUseCase,
            GetEncounterTypeByIdUseCase getByIdUseCase,
            ListEncounterTypeUseCase listUseCase,
            UpdateEncounterTypeUseCase updateUseCase,
            DeleteEncounterTypeUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<EncounterTypeResponse> create(@Valid @RequestBody CreateEncounterTypeRequest request) {
        var command = new RegisterEncounterTypeCommand(
                request.code(),
                request.name(),
                request.active());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<EncounterTypeResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EncounterTypeResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new EncounterTypeId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EncounterTypeResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateEncounterTypeRequest request) {
        var command = new UpdateEncounterTypeCommand(
                new EncounterTypeId(id),
                request.code(),
                request.name(),
                request.active());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new EncounterTypeId(id));
        return ResponseEntity.noContent().build();
    }
}
