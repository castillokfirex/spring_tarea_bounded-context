package com.example.tarea.infrastructure.clinicalnote.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.clinicalnote.command.RegisterClinicalNoteCommand;
import com.example.tarea.application.clinicalnote.command.UpdateClinicalNoteCommand;
import com.example.tarea.application.clinicalnote.dto.ClinicalNoteResponse;
import com.example.tarea.application.clinicalnote.usecase.DeleteClinicalNoteUseCase;
import com.example.tarea.application.clinicalnote.usecase.GetClinicalNoteByIdUseCase;
import com.example.tarea.application.clinicalnote.usecase.ListClinicalNoteUseCase;
import com.example.tarea.application.clinicalnote.usecase.RegisterClinicalNoteUseCase;
import com.example.tarea.application.clinicalnote.usecase.UpdateClinicalNoteUseCase;
import com.example.tarea.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.example.tarea.infrastructure.clinicalnote.adapters.in.rest.dtos.CreateClinicalNoteRequest;
import com.example.tarea.infrastructure.clinicalnote.adapters.in.rest.dtos.UpdateClinicalNoteRequest;
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
@RequestMapping("/api/clinical-notes")
public class ClinicalNoteController {

    private final RegisterClinicalNoteUseCase registerUseCase;
    private final GetClinicalNoteByIdUseCase getByIdUseCase;
    private final ListClinicalNoteUseCase listUseCase;
    private final UpdateClinicalNoteUseCase updateUseCase;
    private final DeleteClinicalNoteUseCase deleteUseCase;

    public ClinicalNoteController(
            RegisterClinicalNoteUseCase registerUseCase,
            GetClinicalNoteByIdUseCase getByIdUseCase,
            ListClinicalNoteUseCase listUseCase,
            UpdateClinicalNoteUseCase updateUseCase,
            DeleteClinicalNoteUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClinicalNoteResponse> create(@Valid @RequestBody CreateClinicalNoteRequest request) {
        var command = new RegisterClinicalNoteCommand(
                request.encounterId(),
                request.professionalId(),
                request.subjective(),
                request.objective(),
                request.assessment(),
                request.plan(),
                request.additionalNotes(),
                request.signedAt());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ClinicalNoteResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalNoteResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ClinicalNoteId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalNoteResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateClinicalNoteRequest request) {
        var command = new UpdateClinicalNoteCommand(
                new ClinicalNoteId(id),
                request.encounterId(),
                request.professionalId(),
                request.subjective(),
                request.objective(),
                request.assessment(),
                request.plan(),
                request.additionalNotes(),
                request.signedAt());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new ClinicalNoteId(id));
        return ResponseEntity.noContent().build();
    }
}
