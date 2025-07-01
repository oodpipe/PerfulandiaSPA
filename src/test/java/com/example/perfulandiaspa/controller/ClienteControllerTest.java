package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.Cliente;
import com.example.perfulandiaspa.services.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("removal") // Oculta la advertencia deprecada de @MockBean
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    // Cliente de ejemplo para reutilizar en todos los tests
    private Cliente crearMockCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan Pérez");
        cliente.setEmail("juan@example.com");
        cliente.setDireccion("Av. Siempre Viva 123");
        cliente.setTelefono("123456789");
        cliente.setFechaRegistro(LocalDate.now());
        return cliente;
    }

    @Test
    void crearCliente() throws Exception {
        Cliente cliente = crearMockCliente();

        Mockito.when(clienteService.crearCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/v1/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$.email").value("juan@example.com"));
    }

    @Test
    void obtenerCliente() throws Exception {
        Cliente cliente = crearMockCliente();

        Mockito.when(clienteService.buscarPorId(1L)).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/api/v1/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Pérez"));
    }

    @Test
    void buscarPorNombre() throws Exception {
        Cliente cliente = crearMockCliente();

        Mockito.when(clienteService.buscarPorNombre("Juan"))
                .thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/v1/clientes/buscar")
                        .param("nombre", "Juan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan Pérez"));
    }

    @Test
    void actualizarCliente() throws Exception {
        Cliente cliente = crearMockCliente();
        cliente.setNombre("Juan Actualizado");

        Mockito.when(clienteService.actualizarCliente(eq(1L), any(Cliente.class)))
                .thenReturn(cliente);

        mockMvc.perform(put("/api/v1/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Actualizado"));
    }

    @Test
    void eliminarCliente() throws Exception {
        Mockito.when(clienteService.eliminarCliente(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/clientes/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void registrarCompraPerfumeria() throws Exception {
        Mockito.doNothing().when(clienteService)
                .registrarCompraEnHistorial(eq(1L), eq("Perfume YSL"));

        mockMvc.perform(post("/api/v1/clientes/1/registrar-compra")
                        .param("detalleCompra", "Perfume YSL"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerComprasCliente() throws Exception {
        Mockito.when(clienteService.obtenerComprasDelCliente(1L))
                .thenReturn(Optional.of(List.of("Perfume A", "Perfume B")));

        mockMvc.perform(get("/api/v1/clientes/1/compras"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Perfume A"))
                .andExpect(jsonPath("$[1]").value("Perfume B"));
    }
}