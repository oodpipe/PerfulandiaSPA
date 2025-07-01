package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.HistorialCliente;
import com.example.perfulandiaspa.services.HistorialClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal")
@WebMvcTest(HistorialClienteController.class)
class HistorialClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistorialClienteService historialService;

    private HistorialCliente crearMockHistorial() {
        HistorialCliente historial = new HistorialCliente();
        historial.setId(1L);
        historial.setAlergiasIngredientes("Alcohol");
        historial.setPreferenciasFragancias("Cítrico");
        historial.setFechaRegistro(LocalDateTime.now());
        historial.setFechaUltimaActualizacion(LocalDateTime.now());
        historial.getCompras().add("Perfume Boss");
        return historial;
    }

    @Test
    void registrarCompra() throws Exception {
        Mockito.doNothing().when(historialService).registrarCompra(eq(1L), eq("Perfume Dior"));

        mockMvc.perform(post("/api/v1/historial-perfumeria/1/compras")
                        .param("detalleCompra", "Perfume Dior"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerHistorialFound() throws Exception {
        HistorialCliente historial = crearMockHistorial();

        Mockito.when(historialService.obtenerHistorial(1L))
                .thenReturn(Optional.of(historial));

        mockMvc.perform(get("/api/v1/historial-perfumeria/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alergiasIngredientes").value("Alcohol"))
                .andExpect(jsonPath("$.preferenciasFragancias").value("Cítrico"))
                .andExpect(jsonPath("$.compras[0]").value("Perfume Boss"));
    }

    @Test
    void obtenerHistorialNotFound() throws Exception {
        Mockito.when(historialService.obtenerHistorial(999L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/historial-perfumeria/999"))
                .andExpect(status().isNotFound());
    }
}