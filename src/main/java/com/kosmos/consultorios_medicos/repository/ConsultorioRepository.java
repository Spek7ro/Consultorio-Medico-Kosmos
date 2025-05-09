package com.kosmos.consultorios_medicos.repository;

import com.kosmos.consultorios_medicos.models.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {

    // Buscar consultorios por numero
    @Query("SELECT c FROM Consultorio c WHERE c.numero = ?1")
    Consultorio findByNumero(int numero);

}
