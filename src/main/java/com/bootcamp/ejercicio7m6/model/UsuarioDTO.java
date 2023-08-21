package com.bootcamp.ejercicio7m6.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioDTO {

    private Long id;

    @NotNull
    @Size(max = 20)
    private String nombre;

    @Size(max = 80)
    private String apellido;

    @NotNull
    @Size(max = 25)
    private String nombreUsuario;

    @NotNull
    @Size(max = 100)
    private String contrasenha;

    @NotNull
    @Size(max = 50)
    private String tipoUsuario;

}
