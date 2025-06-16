package com.example.perfulandiaspa.jparepository;

import com.example.perfulandiaspa.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<Cliente, Integer> {
}
