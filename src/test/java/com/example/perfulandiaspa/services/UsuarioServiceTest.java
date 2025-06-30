package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Usuario;
import com.example.perfulandiaspa.jparepository.UsuarioJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    private UsuarioJpaRepository usuarioJpaRepository;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        // Mockeamos el repositorio
        usuarioJpaRepository = mock(UsuarioJpaRepository.class);

        // Inyectamos el mock en el servicio
        usuarioService = new UsuarioService(usuarioJpaRepository);
    }

    @Test
    void getAllUsuarios() {
        // Datos de prueba
        Usuario usuario1 = new Usuario(1, "Juan Pérez", "juan@example.com", "Empleado", null);
        Usuario usuario2 = new Usuario(2, "Ana Gómez", "ana@example.com", "Gerente", null);

        when(usuarioJpaRepository.findAll()).thenReturn(List.of(usuario1, usuario2));

        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        // Verificamos que se obtienen los usuarios correctamente
        assertEquals(2, usuarios.size());
        verify(usuarioJpaRepository, times(1)).findAll(); // Verifica que findAll se haya llamado una vez
    }

    @Test
    void getUsuarioById() {
        // Simulamos un usuario con ID 1
        Usuario usuario = new Usuario(1, "Juan Pérez", "juan@example.com", "Empleado", null);

        when(usuarioJpaRepository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.getUsuarioById(1);

        // Verificamos que el usuario retornado tenga el ID correcto
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void createUsuario() {
        Usuario usuario = new Usuario(1, "Juan Pérez", "juan@example.com", "Empleado", null);

        when(usuarioJpaRepository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.createUsuario(usuario);

        // Verificamos que el usuario creado sea el correcto
        assertNotNull(result);
        assertEquals("Juan Pérez", result.getNombre());
    }

    @Test
    void updateUsuario() {
        // Datos de prueba
        Usuario usuario = new Usuario(1, "Juan Pérez", "juan@example.com", "Empleado", null);
        usuario.setRol("Gerente");

        when(usuarioJpaRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.updateUsuario(usuario);

        // Verificamos que el rol haya sido actualizado
        assertNotNull(result);
        assertEquals("Gerente", result.getRol());
    }

    @Test
    void deleteUsuario() {
        when(usuarioJpaRepository.existsById(1)).thenReturn(true);

        boolean result = usuarioService.deleteUsuario(1);

        // Verificamos que el usuario haya sido eliminado correctamente
        assertTrue(result);
        verify(usuarioJpaRepository).deleteById(1);
    }

    @Test
    void getUsuariosByRol() {
        Usuario usuario = new Usuario(1, "Juan Pérez", "juan@example.com", "Empleado", null);

        when(usuarioJpaRepository.findByRol("Empleado")).thenReturn(List.of(usuario));

        List<Usuario> result = usuarioService.getUsuariosByRol("Empleado");

        // Verificamos que el rol sea correcto
        assertEquals(1, result.size());
        assertEquals("Empleado", result.get(0).getRol());
    }

    @Test
    void getUsuariosBySucursalId() {
        // Simulamos que tenemos un usuario con sucursal
        Usuario usuario = new Usuario(1, "Juan Pérez", "juan@example.com", "Empleado", null);

        // Cuando se consulta por el ID de sucursal, devolver el usuario
        when(usuarioJpaRepository.findBySucursal_Id(1)).thenReturn(List.of(usuario));

        List<Usuario> result = usuarioService.getUsuariosBySucursalId(1);

        // Verificamos que la sucursal sea la correcta
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
    }
}
