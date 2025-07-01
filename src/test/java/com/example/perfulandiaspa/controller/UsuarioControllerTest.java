package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.Usuario;
import com.example.perfulandiaspa.services.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal")
@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuario mockUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Carlos Soto");
        usuario.setEmail("carlos@empresa.cl");
        usuario.setRol("Gerente");
        return usuario;
    }

    @Test
    void getAllUsuarios() throws Exception {
        Mockito.when(usuarioService.getAllUsuarios()).thenReturn(List.of(mockUsuario()));

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Carlos Soto"));
    }

    @Test
    void getUsuarioById_found() throws Exception {
        Mockito.when(usuarioService.getUsuarioById(1)).thenReturn(mockUsuario());

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rol").value("Gerente"));
    }

    @Test
    void getUsuarioById_notFound() throws Exception {
        Mockito.when(usuarioService.getUsuarioById(99)).thenReturn(null);

        mockMvc.perform(get("/api/v1/usuarios/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUsuariosByRol() throws Exception {
        Mockito.when(usuarioService.getUsuariosByRol("Empleado")).thenReturn(List.of(mockUsuario()));

        mockMvc.perform(get("/api/v1/usuarios/rol/Empleado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rol").value("Gerente")); // Valor hardcodeado por el mock
    }

    @Test
    void getUsuariosBySucursalId() throws Exception {
        Mockito.when(usuarioService.getUsuariosBySucursalId(2)).thenReturn(List.of(mockUsuario()));

        mockMvc.perform(get("/api/v1/usuarios/sucursal/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("carlos@empresa.cl"));
    }

    @Test
    void createUsuario() throws Exception {
        Usuario usuario = mockUsuario();

        Mockito.when(usuarioService.createUsuario(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Carlos Soto"));
    }

    @Test
    void updateUsuario_found() throws Exception {
        Usuario usuario = mockUsuario();
        usuario.setNombre("Carlos Actualizado");

        Mockito.when(usuarioService.updateUsuario(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Carlos Actualizado"));
    }

    @Test
    void updateUsuario_notFound() throws Exception {
        Mockito.when(usuarioService.updateUsuario(any(Usuario.class))).thenReturn(null);

        mockMvc.perform(put("/api/v1/usuarios/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockUsuario())))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUsuario_found() throws Exception {
        Mockito.when(usuarioService.deleteUsuario(1)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteUsuario_notFound() throws Exception {
        Mockito.when(usuarioService.deleteUsuario(99)).thenReturn(false);

        mockMvc.perform(delete("/api/v1/usuarios/99"))
                .andExpect(status().isNotFound());
    }
}