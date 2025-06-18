package com.example.perfulandiaspa.jparepository;

import com.example.perfulandiaspa.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteJpaRepository extends JpaRepository<Cliente, Integer> {

    // Buscar clientes por ID de sucursal
    List<Cliente> findBySucursal_Id(int sucursalId);
}
