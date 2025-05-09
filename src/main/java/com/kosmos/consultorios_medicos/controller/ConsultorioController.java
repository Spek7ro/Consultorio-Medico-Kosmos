package com.kosmos.consultorios_medicos.controller;

import com.kosmos.consultorios_medicos.model.Consultorio;
import com.kosmos.consultorios_medicos.service.ConsultorioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultorios")
@CrossOrigin(origins = "*")
public class ConsultorioController {

    @Autowired
    private ConsultorioService consultorioService;

    @Operation(summary = "Listar todos los consultorios")
    @GetMapping
    public ResponseEntity<List<Consultorio>> listar() {
        return ResponseEntity.ok(consultorioService.listarTodos());
    }

    @Operation(summary = "Obtener un consultorio por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable(name = "id") Long id) {
        return consultorioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo consultorio")
    @PostMapping
    public ResponseEntity<Consultorio> crear(@RequestBody Consultorio consultorio) {
        return ResponseEntity.ok(consultorioService.crear(consultorio));
    }

    @Operation(summary = "Actualizar un consultorio existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Consultorio consultorio) {
        try {
            return ResponseEntity.ok(consultorioService.actualizar(id, consultorio));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un consultorio por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable(name = "id") Long id) {
        consultorioService.eliminar(id);
        return ResponseEntity.ok("Consultorio eliminado con éxito.");
    }

}
