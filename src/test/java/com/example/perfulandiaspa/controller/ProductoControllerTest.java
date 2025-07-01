package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.Producto;
import com.example.perfulandiaspa.services.ProductoService;
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
@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Producto mockProducto() {
        return new Producto(1, "Perfume Dior", "Fragancia floral", 89990.0, 10, "Perfume");
    }

    @Test
    void getAllProductos() throws Exception {
        Mockito.when(productoService.getAllProductos()).thenReturn(List.of(mockProducto()));

        mockMvc.perform(get("/api/v1/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Perfume Dior"));
    }

    @Test
    void getProductoByIdFound() throws Exception {
        Mockito.when(productoService.getProductoById(1)).thenReturn(mockProducto());

        mockMvc.perform(get("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Perfume Dior"));
    }

    @Test
    void getProductoByIdNotFound() throws Exception {
        Mockito.when(productoService.getProductoById(99)).thenReturn(null);

        mockMvc.perform(get("/api/v1/productos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createProducto() throws Exception {
        Producto producto = mockProducto();

        Mockito.when(productoService.createProducto(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Perfume Dior"));
    }

    @Test
    void updateProductoFound() throws Exception {
        Producto producto = mockProducto();
        producto.setNombre("Perfume Actualizado");

        Mockito.when(productoService.updateProducto(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(put("/api/v1/productos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Perfume Actualizado"));
    }

    @Test
    void updateProductoNotFound() throws Exception {
        Producto producto = mockProducto();

        Mockito.when(productoService.updateProducto(any(Producto.class))).thenReturn(null);

        mockMvc.perform(put("/api/v1/productos/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteProductoFound() throws Exception {
        Mockito.when(productoService.deleteProducto(1)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/productos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteProductoNotFound() throws Exception {
        Mockito.when(productoService.deleteProducto(99)).thenReturn(false);

        mockMvc.perform(delete("/api/v1/productos/99"))
                .andExpect(status().isNotFound());
    }
}