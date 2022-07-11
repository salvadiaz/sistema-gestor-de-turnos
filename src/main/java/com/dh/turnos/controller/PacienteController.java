package com.dh.turnos.controller;

import com.dh.turnos.dto.PacienteDTO;
import com.dh.turnos.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> crear(@RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.crearPaciente(pacienteDTO));
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listar() {
        return ResponseEntity.ok(pacienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(pacienteService.buscarPorIdDTO(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> modificar(@PathVariable(name = "id") Long id,
                                                 @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.modificar(id, pacienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name = "id") Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
