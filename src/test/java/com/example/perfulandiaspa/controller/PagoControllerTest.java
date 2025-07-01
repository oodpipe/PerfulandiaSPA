package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.Pago;
import com.example.perfulandiaspa.services.PagoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal")
@WebMvcTest(PagoController.class)
class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagoService pagoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pago mockPago() {
        Pago pago = new Pago();
        pago.setId(1);
        pago.setFecha("2024-06-30");
        pago.setEstado("Pagado");
        pago.setTotal(15990.0);
        return pago;
    }

    @Test
    void getAllPagos() throws Exception {
        Mockito.when(pagoService.getAllPagos()).thenReturn(List.of(mockPago()));

        mockMvc.perform(get("/api/v1/pagos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("Pagado"));
    }

    @Test
    void getPagoById() throws Exception {
        Mockito.when(pagoService.getPagoById(1)).thenReturn(mockPago());

        mockMvc.perform(get("/api/v1/pagos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Pagado"));
    }

    @Test
    void createPago() throws Exception {
        Pago pago = mockPago();

        Mockito.when(pagoService.createPago(any(Pago.class))).thenReturn(pago);

        mockMvc.perform(post("/api/v1/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pago)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(15990.0));
    }

    @Test
    void deletePago() throws Exception {
        Mockito.doNothing().when(pagoService).deletePago(1);

        mockMvc.perform(delete("/api/v1/pagos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getPagosByEstado() throws Exception {
        Mockito.when(pagoService.getPagosByEstado("Pagado")).thenReturn(List.of(mockPago()));

        mockMvc.perform(get("/api/v1/pagos/estado/Pagado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("Pagado"));
    }

    @Test
    void getPagosByCliente() throws Exception {
        Mockito.when(pagoService.getPagosByCliente(5)).thenReturn(Collections.singletonList(mockPago()));

        mockMvc.perform(get("/api/v1/pagos/cliente/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("Pagado"));
    }

    @Test
    void updatePago() throws Exception {
        Pago pago = mockPago();
        pago.setEstado("Pendiente");

        Mockito.when(pagoService.updatePago(any(Pago.class))).thenReturn(pago);

        mockMvc.perform(put("/api/v1/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pago)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Pendiente"));
    }
}