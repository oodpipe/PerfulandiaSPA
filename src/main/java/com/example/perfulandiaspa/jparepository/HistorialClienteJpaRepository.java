package com.example.perfulandiaspa.jparepository;

import com.example.perfulandiaspa.model.HistorialCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistorialClienteJpaRepository extends JpaRepository<HistorialCliente, Long> {

    Optional<HistorialCliente> findByClienteId(Long clienteId);

    @Modifying
    @Query("UPDATE HistorialCliente h SET h.alergias = :alergias WHERE h.id = :id")
    void actualizarAlergias(Long id, String alergias);

    @Query("SELECT h FROM HistorialCliente h WHERE h.condicionesMedicas LIKE %:termino%")
    List<HistorialCliente> buscarPorCondicionMedica(String termino);
}