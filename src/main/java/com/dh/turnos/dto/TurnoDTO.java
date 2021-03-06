package com.dh.turnos.dto;

import com.dh.turnos.model.Turno;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TurnoDTO {
    private Long id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;

    private LocalDateTime fechaHora;

    public TurnoDTO(Turno turno) {
        this.id = turno.getId();
        this.paciente = new PacienteDTO(turno.getPaciente());
        this.odontologo = new OdontologoDTO(turno.getOdontologo());
        this.fechaHora = turno.getFechaHora();
    }

    public TurnoDTO() {
    }
}
