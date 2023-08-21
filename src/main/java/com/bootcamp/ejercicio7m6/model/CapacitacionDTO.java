package com.bootcamp.ejercicio7m6.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CapacitacionDTO {

    private Integer numCapacitacion;

    @NotNull
    private Integer cantidadAsistentes;

    @NotNull
    @Size(max = 255)
    private String detalle;

    @Size(max = 255)
    private String diaSemana;

    private LocalTime duracion;

    private LocalTime hora;

    @NotNull
    @Size(max = 20)
    private String lugar;

    @NotNull
    @Size(max = 255)
    private String nombre;

    @NotNull
    private Long rutCliente;

}
