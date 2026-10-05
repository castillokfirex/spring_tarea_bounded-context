package com.example.tarea.infrastructure.professionalstudy.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.professionalstudy.command.RegisterProfessionalStudyCommand;
import com.example.tarea.application.professionalstudy.command.UpdateProfessionalStudyCommand;
import com.example.tarea.application.professionalstudy.dto.ProfessionalStudyResponse;
import com.example.tarea.application.professionalstudy.usecase.DeleteProfessionalStudyUseCase;
import com.example.tarea.application.professionalstudy.usecase.GetProfessionalStudyByIdUseCase;
import com.example.tarea.application.professionalstudy.usecase.ListProfessionalStudyUseCase;
import com.example.tarea.application.professionalstudy.usecase.RegisterProfessionalStudyUseCase;
import com.example.tarea.application.professionalstudy.usecase.UpdateProfessionalStudyUseCase;
import com.example.tarea.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.example.tarea.infrastructure.professionalstudy.adapters.in.rest.dtos.CreateProfessionalStudyRequest;
import com.example.tarea.infrastructure.professionalstudy.adapters.in.rest.dtos.UpdateProfessionalStudyRequest;
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
@RequestMapping("/api/professional-studies")
public class ProfessionalStudyController {

    private final RegisterProfessionalStudyUseCase registerUseCase;
    private final GetProfessionalStudyByIdUseCase getByIdUseCase;
    private final ListProfessionalStudyUseCase listUseCase;
    private final UpdateProfessionalStudyUseCase updateUseCase;
    private final DeleteProfessionalStudyUseCase deleteUseCase;

    public ProfessionalStudyController(
            RegisterProfessionalStudyUseCase registerUseCase,
            GetProfessionalStudyByIdUseCase getByIdUseCase,
            ListProfessionalStudyUseCase listUseCase,
            UpdateProfessionalStudyUseCase updateUseCase,
            DeleteProfessionalStudyUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ProfessionalStudyResponse> create(@Valid @RequestBody CreateProfessionalStudyRequest request) {
        var command = new RegisterProfessionalStudyCommand(
                request.studyId(),
                request.professionalId(),
                request.title(),
                request.university(),
                request.isValid(),
                request.resolutionNumber(),
                request.countryId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalStudyResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalStudyResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ProfessionalStudyId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalStudyResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateProfessionalStudyRequest request) {
        var command = new UpdateProfessionalStudyCommand(
                new ProfessionalStudyId(id),
                request.studyId(),
                request.professionalId(),
                request.title(),
                request.university(),
                request.isValid(),
                request.resolutionNumber(),
                request.countryId());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new ProfessionalStudyId(id));
        return ResponseEntity.noContent().build();
    }
}
