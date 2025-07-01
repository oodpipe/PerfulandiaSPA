package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.Sucursal;
import com.example.perfulandiaspa.services.SucursalService;
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
@WebMvcTest(SucursalController.class)
class SucursalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SucursalService sucursalService;

    @Autowired
    private ObjectMapper objectMapper;

    private Sucursal mockSucursal() {
        return new Sucursal(1, "Sucursal Centro", "Av. Principal 123", "Santiago", "08:00", "18:00");
    }

    @Test
    void getAllSucursales() throws Exception {
        Mockito.when(sucursalService.getAllSucursales()).thenReturn(List.of(mockSucursal()));

        mockMvc.perform(get("/api/v1/sucursales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Sucursal Centro"));
    }

    @Test
    void getSucursalById_found() throws Exception {
        Mockito.when(sucursalService.getSucursalById(1)).thenReturn(mockSucursal());

        mockMvc.perform(get("/api/v1/sucursales/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ciudad").value("Santiago"));
    }

    @Test
    void getSucursalById_notFound() throws Exception {
        Mockito.when(sucursalService.getSucursalById(99)).thenReturn(null);

        mockMvc.perform(get("/api/v1/sucursales/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getSucursalesByCiudad() throws Exception {
        Mockito.when(sucursalService.getSucursalesByCiudad("Santiago"))
                .thenReturn(List.of(mockSucursal()));

        mockMvc.perform(get("/api/v1/sucursales/ciudad/Santiago"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ciudad").value("Santiago"));
    }

    @Test
    void createSucursal() throws Exception {
        Sucursal sucursal = mockSucursal();

        Mockito.when(sucursalService.createSucursal(any(Sucursal.class))).thenReturn(sucursal);

        mockMvc.perform(post("/api/v1/sucursales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sucursal)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Sucursal Centro"));
    }

    @Test
    void updateSucursal_found() throws Exception {
        Sucursal updated = mockSucursal();
        updated.setNombre("Sucursal Actualizada");

        Mockito.when(sucursalService.updateSucursal(any(Sucursal.class))).thenReturn(updated);

        mockMvc.perform(put("/api/v1/sucursales/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Sucursal Actualizada"));
    }

    @Test
    void updateSucursal_notFound() throws Exception {
        Mockito.when(sucursalService.updateSucursal(any(Sucursal.class))).thenReturn(null);

        mockMvc.perform(put("/api/v1/sucursales/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockSucursal())))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteSucursal_found() throws Exception {
        Mockito.when(sucursalService.deleteSucursal(1)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/sucursales/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteSucursal_notFound() throws Exception {
        Mockito.when(sucursalService.deleteSucursal(99)).thenReturn(false);

        mockMvc.perform(delete("/api/v1/sucursales/99"))
                .andExpect(status().isNotFound());
    }
}