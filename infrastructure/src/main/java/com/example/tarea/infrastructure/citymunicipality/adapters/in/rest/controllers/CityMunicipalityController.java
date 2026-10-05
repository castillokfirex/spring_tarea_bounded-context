package com.example.tarea.infrastructure.citymunicipality.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.citymunicipality.command.RegisterCityMunicipalityCommand;
import com.example.tarea.application.citymunicipality.command.UpdateCityMunicipalityCommand;
import com.example.tarea.application.citymunicipality.dto.CityMunicipalityResponse;
import com.example.tarea.application.citymunicipality.usecase.DeleteCityMunicipalityUseCase;
import com.example.tarea.application.citymunicipality.usecase.GetCityMunicipalityByIdUseCase;
import com.example.tarea.application.citymunicipality.usecase.ListCityMunicipalityUseCase;
import com.example.tarea.application.citymunicipality.usecase.RegisterCityMunicipalityUseCase;
import com.example.tarea.application.citymunicipality.usecase.UpdateCityMunicipalityUseCase;
import com.example.tarea.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.example.tarea.infrastructure.citymunicipality.adapters.in.rest.dtos.CreateCityMunicipalityRequest;
import com.example.tarea.infrastructure.citymunicipality.adapters.in.rest.dtos.UpdateCityMunicipalityRequest;
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
@RequestMapping("/api/city-municipalities")
public class CityMunicipalityController {

    private final RegisterCityMunicipalityUseCase registerUseCase;
    private final GetCityMunicipalityByIdUseCase getByIdUseCase;
    private final ListCityMunicipalityUseCase listUseCase;
    private final UpdateCityMunicipalityUseCase updateUseCase;
    private final DeleteCityMunicipalityUseCase deleteUseCase;

    public CityMunicipalityController(
            RegisterCityMunicipalityUseCase registerUseCase,
            GetCityMunicipalityByIdUseCase getByIdUseCase,
            ListCityMunicipalityUseCase listUseCase,
            UpdateCityMunicipalityUseCase updateUseCase,
            DeleteCityMunicipalityUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<CityMunicipalityResponse> create(@Valid @RequestBody CreateCityMunicipalityRequest request) {
        var command = new RegisterCityMunicipalityCommand(
                request.nameCity(),
                request.codeCity(),
                request.description(),
                request.isActive(),
                request.regionId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<CityMunicipalityResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityMunicipalityResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new CityMunicipalityId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityMunicipalityResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateCityMunicipalityRequest request) {
        var command = new UpdateCityMunicipalityCommand(
                new CityMunicipalityId(id),
                request.nameCity(),
                request.codeCity(),
                request.description(),
                request.isActive(),
                request.regionId());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new CityMunicipalityId(id));
        return ResponseEntity.noContent().build();
    }
}
