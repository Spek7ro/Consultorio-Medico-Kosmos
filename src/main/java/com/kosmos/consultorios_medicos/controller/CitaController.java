package com.kosmos.consultorios_medicos.controller;

import com.kosmos.consultorios_medicos.model.Cita;
import com.kosmos.consultorios_medicos.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Operation(summary = "Listar todas las citas")
    @GetMapping
    public ResponseEntity<List<Cita>> listarTodas() {
        return ResponseEntity.ok(citaService.obtenerCitas());
    }

    @Operation(summary = "Agendar una nueva cita")
    @PostMapping
    public ResponseEntity<?> agendar(@RequestBody Cita cita) {
        try {
            return ResponseEntity.ok(citaService.agendarCita(cita));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener una cita por ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return citaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Consultar citas por fecha
    @Operation(summary = "Consultar citas por fecha (yyyy-MM-dd)")
    @GetMapping("/fecha")
    public ResponseEntity<List<Cita>> obtenerPorFecha(
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        return ResponseEntity.ok(citaService.obtenerCitasPorFecha(fecha));
    }

    // Editar cita
    @Operation(summary = "Actualizar una cita")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cita cita) {
        try {
            return ResponseEntity.ok(citaService.actualizarCita(id, cita));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Cancelar una cita (solo si aún no ha pasado)
    @Operation(summary = "Cancelar una cita pendiente")
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        citaService.cancelarCita(id);
        return ResponseEntity.ok("Cita cancelada");
    }

    // Eliminar cita
    @Operation(summary = "Eliminar una cita")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return ResponseEntity.ok("Cita eliminada.");
    }

    @Operation(summary = "Consultar citas por filtros: fecha, doctorId y/o consultorioId")
    @GetMapping("/buscar")
    public ResponseEntity<List<Cita>> buscarCitas(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,
            @RequestParam(required = false) Long doctorId,
            @RequestParam(required = false) Long consultorioId
    ) {
        List<Cita> citas = citaService.buscarPorFechaDoctorYConsultorio(fecha, doctorId, consultorioId);
        return ResponseEntity.ok(citas);
    }

}
