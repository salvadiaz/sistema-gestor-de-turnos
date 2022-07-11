package com.dh.turnos.service;

import com.dh.turnos.dto.OdontologoDTO;
import com.dh.turnos.dto.PacienteDTO;
import com.dh.turnos.exceptions.NotFoundException;
import com.dh.turnos.model.Domicilio;
import com.dh.turnos.model.Odontologo;
import com.dh.turnos.model.Paciente;
import com.dh.turnos.repository.OdontologoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OdontologoServiceTest {
    @Mock
    OdontologoRepository odontologoRepository;

    @InjectMocks
    OdontologoService odontologoService;

    @Test
    void crearOdontologo() {
        OdontologoDTO odontologoDTO = new OdontologoDTO("salva", "diaz", 1234);
        Odontologo odontologo = new Odontologo(odontologoDTO);
        when(odontologoRepository.save(any(Odontologo.class))).thenReturn(odontologo);

        OdontologoDTO respuesta = odontologoService.crearOdontologo(odontologoDTO);

        Assertions.assertEquals(odontologo.getApellido(), respuesta.getApellido());
        Assertions.assertEquals(odontologo.getNombre(), respuesta.getNombre());
        Assertions.assertEquals(odontologo.getMatricula(), respuesta.getMatricula());
    }

    @Test
    void buscarPorIdDTO() {
        OdontologoDTO odontologoDTO = new OdontologoDTO("salva", "diaz", 1234);
        Odontologo odontologo = new Odontologo(odontologoDTO);
        when(odontologoRepository.findById(any(Long.class))).thenReturn(Optional.of(odontologo));

        OdontologoDTO respuesta = odontologoService.buscarPorIdDTO(1L);

        Assertions.assertEquals(odontologo.getApellido(), respuesta.getApellido());
        Assertions.assertEquals(odontologo.getNombre(), respuesta.getNombre());
        Assertions.assertEquals(odontologo.getMatricula(), respuesta.getMatricula());
    }

    @Test
    void eliminar_odontologo_inexistente() {
        when(odontologoRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        final NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> odontologoService.eliminar(1L));
        Assertions.assertEquals("404 NOT_FOUND \"Odontologo no encontrado\"", exception.getMessage());
    }
}