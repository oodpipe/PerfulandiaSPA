package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.Pedido;
import com.example.perfulandiaspa.services.PedidoService;
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
@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Pedido mockPedido() {
        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setFecha("2024-07-01");
        pedido.setEstado("En proceso");
        pedido.setTotal(25000.0);
        return pedido;
    }

    @Test
    void getAllPedidos() throws Exception {
        Mockito.when(pedidoService.getAllPedidos()).thenReturn(List.of(mockPedido()));

        mockMvc.perform(get("/api/v1/pedidos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("En proceso"));
    }

    @Test
    void getPedidoById_found() throws Exception {
        Mockito.when(pedidoService.getPedidoById(1)).thenReturn(mockPedido());

        mockMvc.perform(get("/api/v1/pedidos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("En proceso"));
    }

    @Test
    void getPedidoById_notFound() throws Exception {
        Mockito.when(pedidoService.getPedidoById(99)).thenReturn(null);

        mockMvc.perform(get("/api/v1/pedidos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getPedidosByCliente() throws Exception {
        Mockito.when(pedidoService.getPedidosByCliente(5)).thenReturn(List.of(mockPedido()));

        mockMvc.perform(get("/api/v1/pedidos/cliente/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("En proceso"));
    }

    @Test
    void getPedidosByEstado() throws Exception {
        Mockito.when(pedidoService.getPedidosByEstado("Enviado")).thenReturn(List.of(mockPedido()));

        mockMvc.perform(get("/api/v1/pedidos/estado/Enviado"))
                .andExpect(status().isOk());
    }

    @Test
    void createPedido() throws Exception {
        Pedido pedido = mockPedido();

        Mockito.when(pedidoService.createPedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(post("/api/v1/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.total").value(25000.0));
    }

    @Test
    void updatePedido_found() throws Exception {
        Pedido pedido = mockPedido();
        pedido.setEstado("Enviado");

        Mockito.when(pedidoService.updatePedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(put("/api/v1/pedidos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Enviado"));
    }

    @Test
    void updatePedido_notFound() throws Exception {
        Pedido pedido = mockPedido();

        Mockito.when(pedidoService.updatePedido(any(Pedido.class))).thenReturn(null);

        mockMvc.perform(put("/api/v1/pedidos/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateEstadoPedido_found() throws Exception {
        Pedido pedido = mockPedido();
        pedido.setEstado("Entregado");

        Mockito.when(pedidoService.updateEstadoPedido(1, "Entregado")).thenReturn(pedido);

        mockMvc.perform(put("/api/v1/pedidos/1/estado/Entregado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("Entregado"));
    }

    @Test
    void updateEstadoPedido_notFound() throws Exception {
        Mockito.when(pedidoService.updateEstadoPedido(99, "Entregado")).thenReturn(null);

        mockMvc.perform(put("/api/v1/pedidos/99/estado/Entregado"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePedido_found() throws Exception {
        Mockito.when(pedidoService.deletePedido(1)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/pedidos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletePedido_notFound() throws Exception {
        Mockito.when(pedidoService.deletePedido(99)).thenReturn(false);

        mockMvc.perform(delete("/api/v1/pedidos/99"))
                .andExpect(status().isNotFound());
    }
}