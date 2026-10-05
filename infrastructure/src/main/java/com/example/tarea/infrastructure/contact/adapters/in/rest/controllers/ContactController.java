package com.example.tarea.infrastructure.contact.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.example.tarea.application.contact.command.RegisterContactCommand;
import com.example.tarea.application.contact.command.UpdateContactCommand;
import com.example.tarea.application.contact.dto.ContactResponse;
import com.example.tarea.application.contact.usecase.DeleteContactUseCase;
import com.example.tarea.application.contact.usecase.GetContactByIdUseCase;
import com.example.tarea.application.contact.usecase.ListContactUseCase;
import com.example.tarea.application.contact.usecase.RegisterContactUseCase;
import com.example.tarea.application.contact.usecase.UpdateContactUseCase;
import com.example.tarea.domain.contact.model.valueobject.ContactId;
import com.example.tarea.infrastructure.contact.adapters.in.rest.dtos.CreateContactRequest;
import com.example.tarea.infrastructure.contact.adapters.in.rest.dtos.UpdateContactRequest;
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
@RequestMapping("/api/contacts")
public class ContactController {

    private final RegisterContactUseCase registerUseCase;
    private final GetContactByIdUseCase getByIdUseCase;
    private final ListContactUseCase listUseCase;
    private final UpdateContactUseCase updateUseCase;
    private final DeleteContactUseCase deleteUseCase;

    public ContactController(
            RegisterContactUseCase registerUseCase,
            GetContactByIdUseCase getByIdUseCase,
            ListContactUseCase listUseCase,
            UpdateContactUseCase updateUseCase,
            DeleteContactUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ContactResponse> create(@Valid @RequestBody CreateContactRequest request) {
        var command = new RegisterContactCommand(
                request.fullName(),
                request.email(),
                request.notes(),
                request.cityId(),
                request.createdBy(),
                request.updatedBy());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ContactResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ContactId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactResponse> update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateContactRequest request) {
        var command = new UpdateContactCommand(
                new ContactId(id),
                request.fullName(),
                request.email(),
                request.notes(),
                request.cityId(),
                request.updatedBy());

        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        deleteUseCase.execute(new ContactId(id));
        return ResponseEntity.noContent().build();
    }
}
