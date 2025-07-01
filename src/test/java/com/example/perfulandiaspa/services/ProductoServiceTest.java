package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.jparepository.ProductoJpaRepository;
import com.example.perfulandiaspa.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    private ProductoJpaRepository productoJpaRepository;
    private ProductoService productoService;

    // Inicializamos los mocks antes de cada prueba
    @BeforeEach
    void setUp() {
        // Mockeamos el repositorio
        productoJpaRepository = mock(ProductoJpaRepository.class);

        // Inyectamos el mock en el servicio
        productoService = new ProductoService(productoJpaRepository);
    }

    @Test
    void getAllProductos() {
        // Datos de prueba
        Producto producto1 = new Producto(1, "Perfume A", "Floral", 19990, 10, "Perfume");
        Producto producto2 = new Producto(2, "Perfume B", "Amaderado", 24990, 5, "Perfume");

        // Simulamos la llamada a findAll
        when(productoJpaRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        // Llamamos al servicio y verificamos la respuesta
        List<Producto> productos = productoService.getAllProductos();
        assertEquals(2, productos.size()); // Verifica que se obtienen dos productos
        verify(productoJpaRepository, times(1)).findAll(); // Verifica que findAll se haya llamado una vez
    }

    @Test
    void getProductoById() {
        // Simulamos un producto con ID 1
        Producto producto = new Producto(1, "Perfume A", "Floral", 19990, 10, "Perfume");

        // Simulamos la respuesta del repositorio
        when(productoJpaRepository.findById(1)).thenReturn(Optional.of(producto));

        // Llamamos al servicio y verificamos el ID del producto
        Producto result = productoService.getProductoById(1);
        assertNotNull(result);
        assertEquals(1, result.getId()); // Verifica que el ID del producto es correcto
    }

    @Test
    void createProducto() {
        Producto producto = new Producto(1, "Perfume A", "Floral", 19990, 10, "Perfume");

        // Simulamos la respuesta del repositorio al guardar el producto
        when(productoJpaRepository.save(Mockito.any(Producto.class))).thenAnswer(i -> i.getArguments()[0]);

        // Llamamos al servicio y verificamos el producto guardado
        Producto result = productoService.createProducto(producto);
        assertEquals("Perfume A", result.getNombre()); // Verifica que el nombre del producto es correcto
        assertEquals(19990, result.getPrecio()); // Verifica que el precio del producto es correcto
    }

    @Test
    void updateProducto() {
        // Datos de prueba
        Producto producto = new Producto(1, "Perfume A", "Floral", 19990, 10, "Perfume");
        producto.setPrecio(24990); // Actualizamos el precio

        // Simulamos la actualización en el repositorio
        when(productoJpaRepository.save(producto)).thenReturn(producto);

        // Llamamos al servicio y verificamos el precio actualizado
        Producto result = productoService.updateProducto(producto);
        assertNotNull(result);
        assertEquals(24990, result.getPrecio()); // Verifica que el precio haya sido actualizado
    }

    @Test
    void deleteProducto() {
        // Simulamos que el producto existe
        when(productoJpaRepository.existsById(1)).thenReturn(true);

        // Llamamos al servicio para eliminar el producto
        boolean result = productoService.deleteProducto(1);
        assertTrue(result); // Verifica que el producto haya sido eliminado correctamente
        verify(productoJpaRepository).deleteById(1); // Verifica que deleteById haya sido llamado
    }
}
