package com.example.perfulandiaspa.jparepository;

import com.example.perfulandiaspa.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteJpaRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findBySucursal_Id(Long sucursalId);

    @Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.historial WHERE c.id = :id")
    Optional<Cliente> findByIdWithHistorial(Long id);

    boolean existsByEmail(String email);

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :termino, '%'))")
    List<Cliente> buscarPorNombre(String termino);
}