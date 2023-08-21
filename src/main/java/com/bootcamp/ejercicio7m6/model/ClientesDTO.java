package com.bootcamp.ejercicio7m6.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClientesDTO {

    private Long idCliente;

    @Size(max = 255)
    private String afp;

    @Size(max = 255)
    private String apellidos;

    @Size(max = 255)
    private String comuna;

    @Size(max = 255)
    private String direccion;

    @Size(max = 255)
    private String edad;

    @Size(max = 12)
    private String rut;

    private Integer sistemaSalud;

    @Size(max = 255)
    private String telefono;

    private Long usuario;

}
