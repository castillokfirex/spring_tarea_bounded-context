package com.example.tarea.infrastructure.medicationroute.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.medicationroute.command.RegisterMedicationRouteCommand;
import com.example.tarea.application.medicationroute.command.UpdateMedicationRouteCommand;
import com.example.tarea.application.medicationroute.dto.MedicationRouteResponse;
import com.example.tarea.application.medicationroute.usecase.DeleteMedicationRouteUseCase;
import com.example.tarea.application.medicationroute.usecase.GetMedicationRouteByIdUseCase;
import com.example.tarea.application.medicationroute.usecase.ListMedicationRouteUseCase;
import com.example.tarea.application.medicationroute.usecase.RegisterMedicationRouteUseCase;
import com.example.tarea.application.medicationroute.usecase.UpdateMedicationRouteUseCase;
import com.example.tarea.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.example.tarea.infrastructure.medicationroute.adapters.in.rest.dtos.CreateMedicationRouteRequest;
import com.example.tarea.infrastructure.medicationroute.adapters.in.rest.dtos.UpdateMedicationRouteRequest;
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
@RequestMapping("/api/medication-routes")
public class MedicationRouteController {

    private final RegisterMedicationRouteUseCase registerUseCase;
    private final GetMedicationRouteByIdUseCase getByIdUseCase;
    private final ListMedicationRouteUseCase listUseCase;
    private final UpdateMedicationRouteUseCase updateUseCase;
    private final DeleteMedicationRouteUseCase deleteUseCase;

    public MedicationRouteController(
            RegisterMedicationRouteUseCase registerUseCase,
            GetMedicationRouteByIdUseCase getByIdUseCase,
            ListMedicationRouteUseCase listUseCase,
            UpdateMedicationRouteUseCase updateUseCase,
            DeleteMedicationRouteUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<MedicationRouteResponse> create(@Valid @RequestBody CreateMedicationRouteRequest request) {
        var command = new RegisterMedicationRouteCommand(
                request.code(),
                request.name(),
                request.active());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<MedicationRouteResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationRouteResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new MedicationRouteId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationRouteResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateMedicationRouteRequest request) {
        var command = new UpdateMedicationRouteCommand(
                new MedicationRouteId(id),
                request.code(),
                request.name(),
                request.active());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new MedicationRouteId(id));
        return ResponseEntity.noContent().build();
    }
}
