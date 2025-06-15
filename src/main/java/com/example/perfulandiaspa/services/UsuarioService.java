package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Usuario;
import com.example.perfulandiaspa.jparepository.UsuarioJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioJpaRepository usuarioJpaRepository;

    // Inyección de dependencias por constructor (mejor práctica)
    public UsuarioService(UsuarioJpaRepository usuarioJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    // Obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioJpaRepository.findAll();
    }

    // Buscar un usuario por su ID
    public Usuario getUsuarioById(int id) {
        return usuarioJpaRepository.findById(id).orElse(null);
    }

    // Guardar un nuevo usuario
    public Usuario createUsuario(Usuario usuario) {
        return usuarioJpaRepository.save(usuario);
    }

    // Actualizar un usuario existente (JPA detecta si el ID ya existe)
    public Usuario updateUsuario(Usuario usuario) {
        return usuarioJpaRepository.save(usuario);
    }

    // Eliminar un usuario por ID, si existe
    public boolean deleteUsuario(int id) {
        if (usuarioJpaRepository.existsById(id)) {
            usuarioJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar usuarios por su rol (requiere método en el JpaRepository)
    public List<Usuario> getUsuariosByRol(String rol) {
        return usuarioJpaRepository.findByRol(rol);
    }

    // Buscar usuarios por sucursal asignada (requiere método en el JpaRepository)
    public List<Usuario> getUsuariosBySucursal(String sucursal) {
        return usuarioJpaRepository.findBySucursalAsignada(sucursal);
    }
}