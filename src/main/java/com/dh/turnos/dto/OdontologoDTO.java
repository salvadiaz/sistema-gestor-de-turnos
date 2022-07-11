package com.dh.turnos.dto;

import com.dh.turnos.model.Odontologo;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class OdontologoDTO {
    private Long id;
    final private String nombre;
    final private String apellido;
    final private Integer matricula;


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
    }
}
