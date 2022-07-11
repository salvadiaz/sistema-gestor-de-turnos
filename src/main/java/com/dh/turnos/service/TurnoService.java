package com.dh.turnos.service;

import com.dh.turnos.dto.NuevoTurnoDTO;
import com.dh.turnos.dto.TurnoDTO;
import com.dh.turnos.exceptions.NotFoundException;
import com.dh.turnos.model.Odontologo;
import com.dh.turnos.model.Paciente;
import com.dh.turnos.model.Turno;
import com.dh.turnos.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoService {
    public static Logger logger = Logger.getLogger(PacienteService.class);

    private final TurnoRepository turnoRepository;

    private final PacienteService pacienteService;

    private final OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    public TurnoDTO crearTurno(NuevoTurnoDTO nuevoTurnoDTO) {
        Paciente paciente = pacienteService.buscarPorId(nuevoTurnoDTO.getPacienteId());
        Odontologo odontologo = odontologoService.buscarPorId(nuevoTurnoDTO.getOdontologoId());

        Turno turno = new Turno(paciente, odontologo, nuevoTurnoDTO.getFechaHora());
        logger.info("Creando turno " + turno);
        return new TurnoDTO(turnoRepository.save(turno));
    }

    public List<TurnoDTO> listar() {
        return turnoRepository.findAll().stream()
                .map(TurnoDTO::new)
                .collect(Collectors.toList());
    }

    public Turno buscarPorId(Long id) {
        return turnoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Turno no encontrado"));
    }

    public TurnoDTO modificar(Long id, TurnoDTO turnoDTO) {
        Turno turno = buscarPorId(id);
        turno.setFechaHora(turnoDTO.getFechaHora());
        turno.setOdontologo(new Odontologo(turnoDTO.getOdontologo()));
        turno.setPaciente(new Paciente(turnoDTO.getPaciente()));

        logger.info("Modificando turno " + turno);

        return new TurnoDTO(turnoRepository.save(turno));
    }

    public void eliminar(Long id) {
        Turno turno = buscarPorId(id);
        logger.info("Eliminando turno " + turno);

        turnoRepository.save(turno);
    }

    public TurnoDTO buscarPorIdDTO(Long id) {
        return new TurnoDTO(buscarPorId(id));
    }
}
