package com.bootcamp.ejercicio7m6.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ContactoDTO {

    private Integer idContacto;

    @NotNull
    @Size(max = 50)
    private String correo;

    @NotNull
    @Size(max = 255)
    private String mensaje;

    @NotNull
    @Size(max = 20)
    private String nombre;

}
