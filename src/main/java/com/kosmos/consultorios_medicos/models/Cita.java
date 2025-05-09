package com.kosmos.consultorios_medicos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "horario_consulta", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioConsulta;

    private String nombrePaciente;

    @Column(name = "estado_cita", nullable = false)
    private boolean cancelada;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "consultorio_id")
    private Consultorio consultorio;

}
