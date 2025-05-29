package com.example.perfulandiaspa.repository;

import com.example.perfulandiaspa.model.Usuario;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepository {
    private List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> findAll() {
        return usuarios;
    }

    public Usuario findById(int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public Usuario save(Usuario usuario) {
        if(usuario.getId() == 0) {
            int maxId = usuarios.stream()
                    .mapToInt(Usuario::getId)
                    .max()
                    .orElse(0);
            usuario.setId(maxId + 1);
        }
        usuarios.add(usuario);
        return usuario;
    }

    public Usuario update(Usuario usuario) {
        Usuario existente = findById(usuario.getId());
        if(existente != null) {
            existente.setNombre(usuario.getNombre());
            existente.setEmail(usuario.getEmail());
            existente.setRol(usuario.getRol());
            existente.setSucursalAsignada(usuario.getSucursalAsignada());
        }
        return existente;
    }

    public boolean delete(int id) {
        return usuarios.removeIf(u -> u.getId() == id);
    }

    public List<Usuario> findByRol(String rol) {
        return usuarios.stream()
                .filter(u -> u.getRol().equalsIgnoreCase(rol))
                .collect(Collectors.toList());
    }

    public List<Usuario> findBySucursal(String sucursal) {
        return usuarios.stream()
                .filter(u -> u.getSucursalAsignada() != null &&
                        u.getSucursalAsignada().equalsIgnoreCase(sucursal))
                .collect(Collectors.toList());
    }
}