package com.dh.turnos.service;

import com.dh.turnos.dto.PacienteDTO;
import com.dh.turnos.exceptions.NotFoundException;
import com.dh.turnos.model.Domicilio;
import com.dh.turnos.model.Paciente;
import com.dh.turnos.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {
    public static Logger logger = Logger.getLogger(PacienteService.class);

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DomicilioService domicilioService;

    public PacienteDTO crearPaciente(PacienteDTO pacienteDTO){
        Paciente paciente = new Paciente(pacienteDTO);
        domicilioService.crearDomicilio(paciente.getDomicilio());
        logger.info("Creando paciente " + paciente);
        return new PacienteDTO(pacienteRepository.save(paciente));
    }

    public List<PacienteDTO> listar() {
        return pacienteRepository.findAll().stream()
                .map(PacienteDTO::new)
                .collect(Collectors.toList());
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente no encontrado"));
    }

    public PacienteDTO modificar(Long id, PacienteDTO pacienteDTO) {
        Paciente paciente = this.buscarPorId(id);
        paciente.setNombre(pacienteDTO.getNombre());
        paciente.setApellido(pacienteDTO.getApellido());
        paciente.setDni(pacienteDTO.getDni());
        paciente.setDomicilio(pacienteDTO.getDomicilio());

        logger.info("Modificando paciente " + paciente);
        return new PacienteDTO(pacienteRepository.save(paciente));
    }

    public void eliminar(Long id) {
        Paciente paciente = this.buscarPorId(id);
        logger.info("Eliminando paciente " + paciente);
        pacienteRepository.delete(paciente);
    }

    public PacienteDTO buscarPorIdDTO(Long id) {
        return new PacienteDTO(buscarPorId(id));
    }
}
