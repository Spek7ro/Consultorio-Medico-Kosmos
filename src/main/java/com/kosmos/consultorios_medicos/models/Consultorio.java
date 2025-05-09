package com.kosmos.consultorios_medicos.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "consultorios")
@Data
public class Consultorio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int numero;
    private int piso;

    @OneToMany(mappedBy = "consultorio")
    private List<Cita> citas;

}
