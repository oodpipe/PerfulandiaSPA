package com.example.perfulandiaspa.jparepository;

import com.example.perfulandiaspa.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SucursalJpaRepository extends JpaRepository<Sucursal, Integer> {
    List<Sucursal> findByCiudad(String ciudad);
}
