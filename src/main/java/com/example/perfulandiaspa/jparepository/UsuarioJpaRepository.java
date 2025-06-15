package com.example.perfulandiaspa.jparepository;

import com.example.perfulandiaspa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioJpaRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByRol(String rol);
    List<Usuario> findBySucursalAsignada(String sucursalAsignada);
}