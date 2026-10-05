package com.example.tarea.infrastructure.professional.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.professional.command.RegisterProfessionalCommand;
import com.example.tarea.application.professional.command.UpdateProfessionalCommand;
import com.example.tarea.application.professional.dto.ProfessionalResponse;
import com.example.tarea.application.professional.usecase.DeleteProfessionalUseCase;
import com.example.tarea.application.professional.usecase.GetProfessionalByIdUseCase;
import com.example.tarea.application.professional.usecase.ListProfessionalUseCase;
import com.example.tarea.application.professional.usecase.RegisterProfessionalUseCase;
import com.example.tarea.application.professional.usecase.UpdateProfessionalUseCase;
import com.example.tarea.domain.professional.model.valueobject.ProfessionalId;
import com.example.tarea.infrastructure.professional.adapters.in.rest.dtos.CreateProfessionalRequest;
import com.example.tarea.infrastructure.professional.adapters.in.rest.dtos.UpdateProfessionalRequest;
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
@RequestMapping("/api/professionals")
public class ProfessionalController {

    private final RegisterProfessionalUseCase registerUseCase;
    private final GetProfessionalByIdUseCase getByIdUseCase;
    private final ListProfessionalUseCase listUseCase;
    private final UpdateProfessionalUseCase updateUseCase;
    private final DeleteProfessionalUseCase deleteUseCase;

    public ProfessionalController(
            RegisterProfessionalUseCase registerUseCase,
            GetProfessionalByIdUseCase getByIdUseCase,
            ListProfessionalUseCase listUseCase,
            UpdateProfessionalUseCase updateUseCase,
            DeleteProfessionalUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ProfessionalResponse> create(@Valid @RequestBody CreateProfessionalRequest request) {
        var command = new RegisterProfessionalCommand(
                request.documentTypeId(),
                request.documentNumber(),
                request.firstName(),
                request.lastName(),
                request.professionalType(),
                request.licenseNumber(),
                request.active(),
                request.cityId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ProfessionalId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateProfessionalRequest request) {
        var command = new UpdateProfessionalCommand(
                new ProfessionalId(id),
                request.documentTypeId(),
                request.documentNumber(),
                request.firstName(),
                request.lastName(),
                request.professionalType(),
                request.licenseNumber(),
                request.active(),
                request.cityId());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new ProfessionalId(id));
        return ResponseEntity.noContent().build();
    }
}
