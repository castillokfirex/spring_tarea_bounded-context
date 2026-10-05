package com.example.tarea.infrastructure.patientallergy.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.patientallergy.command.RegisterPatientAllergyCommand;
import com.example.tarea.application.patientallergy.command.UpdatePatientAllergyCommand;
import com.example.tarea.application.patientallergy.dto.PatientAllergyResponse;
import com.example.tarea.application.patientallergy.usecase.DeletePatientAllergyUseCase;
import com.example.tarea.application.patientallergy.usecase.GetPatientAllergyByIdUseCase;
import com.example.tarea.application.patientallergy.usecase.ListPatientAllergyUseCase;
import com.example.tarea.application.patientallergy.usecase.RegisterPatientAllergyUseCase;
import com.example.tarea.application.patientallergy.usecase.UpdatePatientAllergyUseCase;
import com.example.tarea.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.example.tarea.infrastructure.patientallergy.adapters.in.rest.dtos.CreatePatientAllergyRequest;
import com.example.tarea.infrastructure.patientallergy.adapters.in.rest.dtos.UpdatePatientAllergyRequest;
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
@RequestMapping("/api/patient-allergies")
public class PatientAllergyController {

    private final RegisterPatientAllergyUseCase registerUseCase;
    private final GetPatientAllergyByIdUseCase getByIdUseCase;
    private final ListPatientAllergyUseCase listUseCase;
    private final UpdatePatientAllergyUseCase updateUseCase;
    private final DeletePatientAllergyUseCase deleteUseCase;

    public PatientAllergyController(
            RegisterPatientAllergyUseCase registerUseCase,
            GetPatientAllergyByIdUseCase getByIdUseCase,
            ListPatientAllergyUseCase listUseCase,
            UpdatePatientAllergyUseCase updateUseCase,
            DeletePatientAllergyUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<PatientAllergyResponse> create(@Valid @RequestBody CreatePatientAllergyRequest request) {
        var command = new RegisterPatientAllergyCommand(
                request.patientId(),
                request.substance(),
                request.reaction(),
                request.severity(),
                request.active(),
                request.recordedAt(),
                request.recordedBy());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<PatientAllergyResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientAllergyResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new PatientAllergyId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientAllergyResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdatePatientAllergyRequest request) {
        var command = new UpdatePatientAllergyCommand(
                new PatientAllergyId(id),
                request.patientId(),
                request.substance(),
                request.reaction(),
                request.severity(),
                request.active(),
                request.recordedAt(),
                request.recordedBy());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new PatientAllergyId(id));
        return ResponseEntity.noContent().build();
    }
}
