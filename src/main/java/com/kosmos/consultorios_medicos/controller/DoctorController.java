package com.kosmos.consultorios_medicos.controller;

import com.kosmos.consultorios_medicos.model.Doctor;
import com.kosmos.consultorios_medicos.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Operation(summary = "Listar todos los doctores")
    @GetMapping
    public ResponseEntity<List<Doctor>> listar() {
        return ResponseEntity.ok(doctorService.listarTodos());
    }

    @Operation(summary = "Obtener un doctor por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable(name = "id") Long id) {
        return doctorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo doctor")
    @PostMapping
    public ResponseEntity<Doctor> crear(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.crear(doctor));
    }

    @Operation(summary = "Actualizar un doctor existente")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable(name = "id") Long id, @RequestBody Doctor doctor) {
        try {
            return ResponseEntity.ok(doctorService.actualizar(id, doctor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un doctor por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable(name = "id") Long id) {
        doctorService.eliminar(id);
        return ResponseEntity.ok("Doctor eliminado con éxito.");
    }
}
