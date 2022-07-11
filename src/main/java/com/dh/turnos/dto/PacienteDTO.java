package com.dh.turnos.dto;

import com.dh.turnos.model.Domicilio;
import com.dh.turnos.model.Paciente;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PacienteDTO {
    private Long id;
    final private String nombre;
    final private String apellido;
    final private String dni;

    private LocalDate fechaIngreso;

    final private Domicilio domicilio;


    public PacienteDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nombre = paciente.getNombre();
        this.apellido = paciente.getApellido();
        this.dni = paciente.getDni();
        this.fechaIngreso = paciente.getFechaIngreso();
        this.domicilio = paciente.getDomicilio();
    }

    @JsonCreator
    public PacienteDTO(String nombre, String apellido, String dni, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
    }
}
