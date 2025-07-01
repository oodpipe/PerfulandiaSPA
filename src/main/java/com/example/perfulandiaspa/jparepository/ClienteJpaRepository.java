package com.example.perfulandiaspa.jparepository;

import com.example.perfulandiaspa.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional; // Importación añadida

@Repository
public interface ClienteJpaRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.historialPerfumeria WHERE c.id = :id")
    Optional<Cliente> findByIdWithHistorial(Long id);

    // Métodos adicionales que podrías necesitar
    boolean existsByEmail(String email);

    List<Cliente> findBySucursal_Id(Long sucursalId);
}