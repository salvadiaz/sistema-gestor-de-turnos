package com.dh.turnos.controller;

import com.dh.turnos.dto.OdontologoDTO;
import com.dh.turnos.service.OdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<OdontologoDTO> crear(@RequestBody OdontologoDTO odontologoDTO) {
        return ResponseEntity.ok(odontologoService.crearOdontologo(odontologoDTO));
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> listar() {
        return ResponseEntity.ok(odontologoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(odontologoService.buscarPorIdDTO(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OdontologoDTO> modificar(@PathVariable(name = "id") Long id,
                                                   @RequestBody OdontologoDTO odontologoDTO) {
        return ResponseEntity.ok(odontologoService.modificar(id, odontologoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable(name = "id") Long id) {
        odontologoService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
