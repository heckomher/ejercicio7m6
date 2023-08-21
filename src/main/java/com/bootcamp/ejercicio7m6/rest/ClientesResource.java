package com.bootcamp.ejercicio7m6.rest;

import com.bootcamp.ejercicio7m6.model.ClientesDTO;
import com.bootcamp.ejercicio7m6.service.ClientesService;
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
@RequestMapping(value = "/api/clientess", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientesResource {

    private final ClientesService clientesService;

    public ClientesResource(final ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public ResponseEntity<List<ClientesDTO>> getAllClientess() {
        return ResponseEntity.ok(clientesService.findAll());
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClientesDTO> getClientes(@PathVariable final Long idCliente) {
        return ResponseEntity.ok(clientesService.get(idCliente));
    }

    @PostMapping
    public ResponseEntity<Long> createClientes(@RequestBody @Valid final ClientesDTO clientesDTO) {
        final Long createdIdCliente = clientesService.create(clientesDTO);
        return new ResponseEntity<>(createdIdCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Long> updateClientes(@PathVariable final Long idCliente,
            @RequestBody @Valid final ClientesDTO clientesDTO) {
        clientesService.update(idCliente, clientesDTO);
        return ResponseEntity.ok(idCliente);
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deleteClientes(@PathVariable final Long idCliente) {
        clientesService.delete(idCliente);
        return ResponseEntity.noContent().build();
    }

}
