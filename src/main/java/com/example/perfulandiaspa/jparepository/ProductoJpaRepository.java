package com.example.perfulandiaspa.jparepository;

import com.example.perfulandiaspa.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoJpaRepository extends JpaRepository<Producto, Integer> {
}