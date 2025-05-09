package com.kosmos.consultorios_medicos.service;

import com.kosmos.consultorios_medicos.models.Consultorio;
import com.kosmos.consultorios_medicos.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultorioService {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    public List<Consultorio> listarTodos() {
        return consultorioRepository.findAll();
    }

    public Optional<Consultorio> obtenerPorId(Long id) {
        return consultorioRepository.findById(id);
    }

    public Consultorio crear(Consultorio consultorio) {
        return consultorioRepository.save(consultorio);
    }

    public Consultorio actualizar(Long id, Consultorio datosActualizados) throws Exception {
        return consultorioRepository.findById(id).map(c -> {
            c.setNumero(datosActualizados.getNumero());
            c.setPiso(datosActualizados.getPiso());
            return consultorioRepository.save(c);
        }).orElseThrow(() -> new Exception("Consultorio no encontrado con ID: " + id));
    }

    public void eliminar(Long id) {
        consultorioRepository.deleteById(id);
    }
}
