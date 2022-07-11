package com.dh.turnos.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NuevoTurnoDTO {
    final private Long pacienteId;
    final private Long odontologoId;
    final private LocalDateTime fechaHora;

    @JsonCreator
    public NuevoTurnoDTO(Long pacienteId, Long odontologoId, LocalDateTime fechaHora) {
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
        this.fechaHora = fechaHora;
    }
}
