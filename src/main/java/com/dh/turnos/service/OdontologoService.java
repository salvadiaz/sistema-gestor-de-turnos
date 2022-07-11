package com.dh.turnos.service;

import com.dh.turnos.dto.OdontologoDTO;
import com.dh.turnos.exceptions.NotFoundException;
import com.dh.turnos.model.Odontologo;
import com.dh.turnos.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OdontologoService {
    public static Logger logger = Logger.getLogger(PacienteService.class);

    @Autowired
    private OdontologoRepository odontologoRepository;

    public OdontologoDTO crearOdontologo(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = new Odontologo(odontologoDTO);
        logger.info("Creando odontologo " + odontologo);
        return new OdontologoDTO(odontologoRepository.save(odontologo));
    }

    public List<OdontologoDTO> listar() {
        return odontologoRepository.findAll().stream()
                .map(OdontologoDTO::new)
                .collect(Collectors.toList());
    }

    public Odontologo buscarPorId(Long id) {
        return odontologoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Odontologo no encontrado"));
    }

    public OdontologoDTO modificar(Long id, OdontologoDTO odontologoDTO) {
        Odontologo odontologo = this.buscarPorId(id);
        odontologo.setNombre(odontologoDTO.getNombre());
        odontologo.setApellido(odontologoDTO.getApellido());
        odontologo.setMatricula(odontologoDTO.getMatricula());

        logger.info("Actualizando odontologo " + odontologo);

        return new OdontologoDTO(odontologoRepository.save(odontologo));
    }

    public void eliminar(Long id) {
        Odontologo odontologo = this.buscarPorId(id);
        logger.info("Eliminando odontologo" + odontologo);
        odontologoRepository.delete(odontologo);
    }

    public OdontologoDTO buscarPorIdDTO(Long id) {
        return new OdontologoDTO(buscarPorId(id));
    }
}
