package com.kosmos.consultorios_medicos.service;

import com.kosmos.consultorios_medicos.models.Doctor;
import com.kosmos.consultorios_medicos.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> listarTodos() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> obtenerPorId(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor crear(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor actualizar(Long id, Doctor datosActualizados) throws Exception {
        return doctorRepository.findById(id).map(doc -> {
            doc.setNombre(datosActualizados.getNombre());
            doc.setApellidoPaterno(datosActualizados.getApellidoPaterno());
            doc.setApellidoMaterno(datosActualizados.getApellidoMaterno());
            doc.setEspecialidad(datosActualizados.getEspecialidad());
            return doctorRepository.save(doc);
        }).orElseThrow(() -> new Exception("Doctor no encontrado con ID: " + id));
    }

    public void eliminar(Long id) {
        doctorRepository.deleteById(id);
    }
}
