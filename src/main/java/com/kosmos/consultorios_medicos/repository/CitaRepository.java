package com.kosmos.consultorios_medicos.repository;

import com.kosmos.consultorios_medicos.models.Cita;
import com.kosmos.consultorios_medicos.models.Consultorio;
import com.kosmos.consultorios_medicos.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Todas las citas de un doctor en un día
    @Query("SELECT c FROM Cita c WHERE c.horarioConsulta >= ?1 AND c.horarioConsulta <= ?2 AND c.doctor = ?3")
    List<Cita> findByDoctorAndHorarioBetween(Doctor doctor, Date inicio, Date fin);

    // Todas las citas de un consultorio en una hora exacta
    @Query("SELECT c FROM Cita c WHERE c.horarioConsulta = ?1 AND c.consultorio = ?2")
    List<Cita> findByConsultorioAndHorario(Consultorio consultorio, Date horario);

    // Todas las citas por nombre de paciente y fecha
    @Query("SELECT c FROM Cita c WHERE c.horarioConsulta >= ?1 AND c.horarioConsulta <= ?2 AND c.nombrePaciente = ?3")
    List<Cita> findByNombrePacienteAndHorarioBetween(String nombrePaciente, Date inicio, Date fin);

    // Todas las citas en una fecha (para consulta general)
    @Query("SELECT c FROM Cita c WHERE c.horarioConsulta >= ?1 AND c.horarioConsulta <= ?2")
    List<Cita> findByHorarioBetween(Date inicio, Date fin);

}
