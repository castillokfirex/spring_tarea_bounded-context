package com.example.tarea.infrastructure.clinicalrecordstatus.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.clinicalrecordstatus.command.RegisterClinicalRecordStatusCommand;
import com.example.tarea.application.clinicalrecordstatus.command.UpdateClinicalRecordStatusCommand;
import com.example.tarea.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import com.example.tarea.application.clinicalrecordstatus.usecase.DeleteClinicalRecordStatusUseCase;
import com.example.tarea.application.clinicalrecordstatus.usecase.GetClinicalRecordStatusByIdUseCase;
import com.example.tarea.application.clinicalrecordstatus.usecase.ListClinicalRecordStatusUseCase;
import com.example.tarea.application.clinicalrecordstatus.usecase.RegisterClinicalRecordStatusUseCase;
import com.example.tarea.application.clinicalrecordstatus.usecase.UpdateClinicalRecordStatusUseCase;
import com.example.tarea.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.example.tarea.infrastructure.clinicalrecordstatus.adapters.in.rest.dtos.CreateClinicalRecordStatusRequest;
import com.example.tarea.infrastructure.clinicalrecordstatus.adapters.in.rest.dtos.UpdateClinicalRecordStatusRequest;
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
@RequestMapping("/api/clinical-record-statusses")
public class ClinicalRecordStatusController {

    private final RegisterClinicalRecordStatusUseCase registerUseCase;
    private final GetClinicalRecordStatusByIdUseCase getByIdUseCase;
    private final ListClinicalRecordStatusUseCase listUseCase;
    private final UpdateClinicalRecordStatusUseCase updateUseCase;
    private final DeleteClinicalRecordStatusUseCase deleteUseCase;

    public ClinicalRecordStatusController(
            RegisterClinicalRecordStatusUseCase registerUseCase,
            GetClinicalRecordStatusByIdUseCase getByIdUseCase,
            ListClinicalRecordStatusUseCase listUseCase,
            UpdateClinicalRecordStatusUseCase updateUseCase,
            DeleteClinicalRecordStatusUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClinicalRecordStatusResponse> create(@Valid @RequestBody CreateClinicalRecordStatusRequest request) {
        var command = new RegisterClinicalRecordStatusCommand(
                request.code(),
                request.name(),
                request.active());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ClinicalRecordStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalRecordStatusResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ClinicalRecordStatusId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalRecordStatusResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateClinicalRecordStatusRequest request) {
        var command = new UpdateClinicalRecordStatusCommand(
                new ClinicalRecordStatusId(id),
                request.code(),
                request.name(),
                request.active());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new ClinicalRecordStatusId(id));
        return ResponseEntity.noContent().build();
    }
}
