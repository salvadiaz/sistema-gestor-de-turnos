package com.dh.turnos.service;

import com.dh.turnos.dto.PacienteDTO;
import com.dh.turnos.exceptions.NotFoundException;
import com.dh.turnos.model.Domicilio;
import com.dh.turnos.model.Paciente;
import com.dh.turnos.repository.PacienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {
    @Mock
    PacienteRepository pacienteRepository;

    @Mock
    DomicilioService domicilioService;

    @InjectMocks
    PacienteService pacienteService;

    @Test
    void crearPaciente() {
        Domicilio domicilio = new Domicilio();
        PacienteDTO pacienteDTO = new PacienteDTO("salva", "diaz", "1234", domicilio);
        Paciente paciente = new Paciente(pacienteDTO);
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        PacienteDTO respuesta = pacienteService.crearPaciente(pacienteDTO);

        Assertions.assertEquals(pacienteDTO.getApellido(), respuesta.getApellido());
        Assertions.assertEquals(pacienteDTO.getNombre(), respuesta.getNombre());
        Assertions.assertEquals(pacienteDTO.getDni(), respuesta.getDni());
    }

    @Test
    void buscarPorId() {
        Domicilio domicilio = new Domicilio();
        Paciente paciente = new Paciente("salva", "diaz", "1234", domicilio);
        when(pacienteRepository.findById(any(Long.class))).thenReturn(Optional.of(paciente));

        Paciente respuesta = pacienteService.buscarPorId(1L);

        Assertions.assertEquals(paciente.getApellido(), respuesta.getApellido());
        Assertions.assertEquals(paciente.getNombre(), respuesta.getNombre());
        Assertions.assertEquals(paciente.getDni(), respuesta.getDni());
    }

    @Test
    void eliminar_paciente_inexistente() {
        when(pacienteRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        final NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> pacienteService.eliminar(1L));
        Assertions.assertEquals("404 NOT_FOUND \"Paciente no encontrado\"", exception.getMessage());
    }
}