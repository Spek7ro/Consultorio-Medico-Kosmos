package com.kosmos.consultorios_medicos.service;

import com.kosmos.consultorios_medicos.model.Cita;
import com.kosmos.consultorios_medicos.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    public Cita agendarCita(Cita nuevaCita) throws Exception {
        Date horario = nuevaCita.getHorarioConsulta();

        // Regla 1: No más de 8 citas por doctor al día
        Date inicioDia = getInicioDelDia(horario);
        Date finDia = getFinDelDia(horario);

        List<Cita> citasDelDoctor = citaRepository.findByDoctorAndHorarioBetween(nuevaCita.getDoctor(), inicioDia, finDia);
        if (citasDelDoctor.size() >= 8) {
            throw new Exception("El doctor ya tiene 8 citas programadas para ese día.");
        }

        // Regla 2: No se puede agendar una cita con el mismo doctor a la misma hora
        boolean doctorOcupado = citasDelDoctor.stream()
                .anyMatch(c -> c.getHorarioConsulta().equals(horario));
        if (doctorOcupado) {
            throw new Exception("El doctor ya tiene una cita a esa hora.");
        }

        // Regla 3: No se puede agendar cita en el mismo consultorio a la misma hora
        List<Cita> citasEnConsultorio = citaRepository.findByConsultorioAndHorario(nuevaCita.getConsultorio(), horario);
        if (!citasEnConsultorio.isEmpty()) {
            throw new Exception("El consultorio ya está ocupado a esa hora.");
        }

        // Regla 4: El paciente no puede tener dos citas el mismo día con menos de 2 horas de diferencia
        List<Cita> citasPaciente = citaRepository.findByNombrePacienteAndHorarioBetween(
                nuevaCita.getNombrePaciente(), inicioDia, finDia);

        Calendar calNueva = Calendar.getInstance();
        calNueva.setTime(horario);

        for (Cita cita : citasPaciente) {
            Calendar calExistente = Calendar.getInstance();
            calExistente.setTime(cita.getHorarioConsulta());

            long diffMillis = Math.abs(calNueva.getTimeInMillis() - calExistente.getTimeInMillis());
            long diffHoras = diffMillis / (1000 * 60 * 60);
            if (diffHoras < 2) {
                throw new Exception("El paciente ya tiene otra cita con menos de 2 horas de diferencia.");
            }
        }
        return citaRepository.save(nuevaCita);
    }

    // Obtener citas por fechas
    public List<Cita> obtenerCitasPorFecha(Date fecha) {
        return citaRepository.findByHorarioBetween(getInicioDelDia(fecha), getFinDelDia(fecha));
    }

    // Cancelar cita
    public void cancelarCita(Long id) {
        citaRepository.findById(id).ifPresent(cita -> {
            Date ahora = new Date();
            if (cita.getHorarioConsulta().after(ahora) && !cita.isEstadoCita()) {
                cita.setEstadoCita(true);
                citaRepository.save(cita);
            }
        });
    }

    // Obtener todas las citas
    public List<Cita> obtenerCitas() {
        return citaRepository.findAll();
    }

    // Obtener citas id
    public Optional<Cita> obtenerPorId(Long id) {
        return citaRepository.findById(id);
    }

    // actualizar cita
    public Cita actualizarCita(Long id, Cita datosActualizados) throws Exception {
        return citaRepository.findById(id).map(cita -> {
            cita.setHorarioConsulta(datosActualizados.getHorarioConsulta());
            cita.setNombrePaciente(datosActualizados.getNombrePaciente());
            cita.setDoctor(datosActualizados.getDoctor());
            cita.setConsultorio(datosActualizados.getConsultorio());
            return citaRepository.save(cita);
        }).orElseThrow(() -> new Exception("Cita no encontrada con ID: " + id));
    }

    // eliminar cita
    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }

    private Date getInicioDelDia(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private Date getFinDelDia(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    // Consultar citas por fecha, doctor y consultorio
    public List<Cita> buscarPorFechaDoctorYConsultorio(Date fecha, Long doctorId, Long consultorioId) {
        Date inicioDia = getInicioDelDia(fecha);
        Date finDia = getFinDelDia(fecha);
        List<Cita> citas = citaRepository.findByHorarioBetween(inicioDia, finDia);

        return citas.stream()
                .filter(c -> (doctorId == null || c.getDoctor().getId().equals(doctorId)) &&
                        (consultorioId == null || c.getConsultorio().getId().equals(consultorioId)))
                .toList();
    }
}
