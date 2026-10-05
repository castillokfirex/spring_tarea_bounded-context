package com.example.tarea.infrastructure.stateregion.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.stateregion.command.RegisterStateRegionCommand;
import com.example.tarea.application.stateregion.command.UpdateStateRegionCommand;
import com.example.tarea.application.stateregion.dto.StateRegionResponse;
import com.example.tarea.application.stateregion.usecase.DeleteStateRegionUseCase;
import com.example.tarea.application.stateregion.usecase.GetStateRegionByIdUseCase;
import com.example.tarea.application.stateregion.usecase.ListStateRegionUseCase;
import com.example.tarea.application.stateregion.usecase.RegisterStateRegionUseCase;
import com.example.tarea.application.stateregion.usecase.UpdateStateRegionUseCase;
import com.example.tarea.domain.stateregion.model.valueobject.StateRegionId;
import com.example.tarea.infrastructure.stateregion.adapters.in.rest.dtos.CreateStateRegionRequest;
import com.example.tarea.infrastructure.stateregion.adapters.in.rest.dtos.UpdateStateRegionRequest;
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
@RequestMapping("/api/state-regions")
public class StateRegionController {

    private final RegisterStateRegionUseCase registerUseCase;
    private final GetStateRegionByIdUseCase getByIdUseCase;
    private final ListStateRegionUseCase listUseCase;
    private final UpdateStateRegionUseCase updateUseCase;
    private final DeleteStateRegionUseCase deleteUseCase;

    public StateRegionController(
            RegisterStateRegionUseCase registerUseCase,
            GetStateRegionByIdUseCase getByIdUseCase,
            ListStateRegionUseCase listUseCase,
            UpdateStateRegionUseCase updateUseCase,
            DeleteStateRegionUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<StateRegionResponse> create(@Valid @RequestBody CreateStateRegionRequest request) {
        var command = new RegisterStateRegionCommand(
                request.nameRegion(),
                request.codeRegion(),
                request.description(),
                request.isActive(),
                request.countryId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<StateRegionResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateRegionResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new StateRegionId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateRegionResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateStateRegionRequest request) {
        var command = new UpdateStateRegionCommand(
                new StateRegionId(id),
                request.nameRegion(),
                request.codeRegion(),
                request.description(),
                request.isActive(),
                request.countryId());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new StateRegionId(id));
        return ResponseEntity.noContent().build();
    }
}
