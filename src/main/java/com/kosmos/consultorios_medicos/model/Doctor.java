package com.kosmos.consultorios_medicos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "doctores")
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String especialidad;

    @OneToMany(mappedBy = "doctor")
    private List<Cita> citas;

}
