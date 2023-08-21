package com.bootcamp.ejercicio7m6.rest;

import com.bootcamp.ejercicio7m6.model.CapacitacionDTO;
import com.bootcamp.ejercicio7m6.service.CapacitacionService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/api/capacitacions", produces = MediaType.APPLICATION_JSON_VALUE)
public class CapacitacionResource {

    private final CapacitacionService capacitacionService;

    public CapacitacionResource(final CapacitacionService capacitacionService) {
        this.capacitacionService = capacitacionService;
    }

    @GetMapping
    public ResponseEntity<List<CapacitacionDTO>> getAllCapacitacions() {
        return ResponseEntity.ok(capacitacionService.findAll());
    }

    @GetMapping("/{numCapacitacion}")
    public ResponseEntity<CapacitacionDTO> getCapacitacion(
            @PathVariable final Integer numCapacitacion) {
        return ResponseEntity.ok(capacitacionService.get(numCapacitacion));
    }

    @PostMapping
    public ResponseEntity<Integer> createCapacitacion(
            @RequestBody @Valid final CapacitacionDTO capacitacionDTO) {
        final Integer createdNumCapacitacion = capacitacionService.create(capacitacionDTO);
        return new ResponseEntity<>(createdNumCapacitacion, HttpStatus.CREATED);
    }

    @PutMapping("/{numCapacitacion}")
    public ResponseEntity<Integer> updateCapacitacion(@PathVariable final Integer numCapacitacion,
            @RequestBody @Valid final CapacitacionDTO capacitacionDTO) {
        capacitacionService.update(numCapacitacion, capacitacionDTO);
        return ResponseEntity.ok(numCapacitacion);
    }

    @DeleteMapping("/{numCapacitacion}")
    public ResponseEntity<Void> deleteCapacitacion(@PathVariable final Integer numCapacitacion) {
        capacitacionService.delete(numCapacitacion);
        return ResponseEntity.noContent().build();
    }

}
