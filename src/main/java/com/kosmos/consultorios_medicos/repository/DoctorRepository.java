package com.kosmos.consultorios_medicos.repository;

import com.kosmos.consultorios_medicos.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Buscar doctores por especialidad
    @Query("SELECT d FROM Doctor d WHERE d.especialidad = ?1")
    List<Doctor> findByEspecialidad(String especialidad);

}
