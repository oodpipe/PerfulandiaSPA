package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Usuario;
import com.example.perfulandiaspa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepository.update(usuario);
    }

    public boolean deleteUsuario(int id) {
        return usuarioRepository.delete(id);
    }

    public List<Usuario> getUsuariosByRol(String rol) {
        return usuarioRepository.findByRol(rol);
    }

    public List<Usuario> getUsuariosBySucursal(String sucursal) {
        return usuarioRepository.findBySucursal(sucursal);
    }
}