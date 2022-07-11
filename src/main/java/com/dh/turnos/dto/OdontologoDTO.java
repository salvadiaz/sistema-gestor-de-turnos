package com.dh.turnos.dto;

import com.dh.turnos.model.Odontologo;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class OdontologoDTO {
    private Long id;
    final private String nombre;
    final private String apellido;
    final private Integer matricula;

    private Set<TurnoDTO> turnos;

    @JsonCreator
    public OdontologoDTO(String nombre, String apellido, Integer matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.matricula = matricula;
    }

    public OdontologoDTO(Odontologo odontologo) {
        this.id = odontologo.getId();
        this.nombre = odontologo.getNombre();
        this.apellido = odontologo.getApellido();
        this.matricula = odontologo.getMatricula();
        this.turnos = odontologo.getTurnos().stream()
                .map(TurnoDTO::new)
                .collect(Collectors.toSet());
    }
}
