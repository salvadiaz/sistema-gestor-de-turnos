package com.dh.turnos.controller;

import com.dh.turnos.dto.NuevoTurnoDTO;
import com.dh.turnos.dto.TurnoDTO;
import com.dh.turnos.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<TurnoDTO> crear(@RequestBody NuevoTurnoDTO nuevoTurnoDTO) {
        return ResponseEntity.ok(turnoService.crearTurno(nuevoTurnoDTO));
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listar() {
        return ResponseEntity.ok(turnoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(turnoService.buscarPorIdDTO(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnoDTO> modificar(@PathVariable(name = "id") Long id,
                                                   @RequestBody TurnoDTO turnoDTO) {
        return ResponseEntity.ok(turnoService.modificar(id, turnoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name = "id") Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
