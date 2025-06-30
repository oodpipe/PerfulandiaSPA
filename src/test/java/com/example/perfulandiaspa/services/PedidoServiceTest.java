package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.jparepository.PedidoJpaRepository;
import com.example.perfulandiaspa.jparepository.ProductoJpaRepository;
import com.example.perfulandiaspa.model.Cliente;
import com.example.perfulandiaspa.model.Pedido;
import com.example.perfulandiaspa.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServiceTest {

    private PedidoJpaRepository pedidoJpaRepository;
    private ProductoJpaRepository productoJpaRepository;
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        // Mockeamos los repositorios
        pedidoJpaRepository = mock(PedidoJpaRepository.class);
        productoJpaRepository = mock(ProductoJpaRepository.class);

        // Inyectamos los mocks en el servicio
        pedidoService = new PedidoService(pedidoJpaRepository, productoJpaRepository);
    }

    @Test
    void getAllPedidos() {
        // Datos de prueba
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();

        when(pedidoJpaRepository.findAll()).thenReturn(Arrays.asList(pedido1, pedido2));

        List<Pedido> pedidos = pedidoService.getAllPedidos();

        // Verificamos que se obtienen los pedidos correctamente
        assertEquals(2, pedidos.size());
        verify(pedidoJpaRepository, times(1)).findAll(); // Verifica que findAll se haya llamado una vez
    }

    @Test
    void getPedidoById() {
        // Simulamos un pedido con ID 1
        Pedido pedido = new Pedido();
        pedido.setId(1);

        when(pedidoJpaRepository.findById(1)).thenReturn(Optional.of(pedido));

        Pedido result = pedidoService.getPedidoById(1);

        // Verificamos que el pedido retornado tenga el ID correcto
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void createPedido() {
        Producto p1 = new Producto(1, "Perfume A", "Floral", 19990, 10, "Perfume");
        Producto p2 = new Producto(2, "Perfume B", "Amaderado", 24990, 5, "Perfume");

        when(productoJpaRepository.findById(1)).thenReturn(Optional.of(p1));
        when(productoJpaRepository.findById(2)).thenReturn(Optional.of(p2));

        Pedido pedido = new Pedido();
        pedido.setProductos(Arrays.asList(p1, p2));

        when(pedidoJpaRepository.save(Mockito.any(Pedido.class))).thenAnswer(i -> i.getArguments()[0]);

        Pedido result = pedidoService.createPedido(pedido);

        // Verificamos que el total sea el correcto
        assertEquals(44980, result.getTotal());
        assertEquals(2, result.getProductos().size());
    }

    @Test
    void updatePedido() {
        // Datos de prueba
        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setEstado("Enviado");

        when(pedidoJpaRepository.save(pedido)).thenReturn(pedido);

        Pedido result = pedidoService.updatePedido(pedido);

        // Verificamos que el estado haya sido actualizado
        assertNotNull(result);
        assertEquals("Enviado", result.getEstado());
    }

    @Test
    void deletePedido() {
        when(pedidoJpaRepository.existsById(1)).thenReturn(true);

        boolean result = pedidoService.deletePedido(1);

        // Verificamos que el pedido haya sido eliminado correctamente
        assertTrue(result);
        verify(pedidoJpaRepository).deleteById(1);
    }

    @Test
    void getPedidosByCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);

        when(pedidoJpaRepository.findByCliente_Id(1)).thenReturn(List.of(pedido));

        List<Pedido> result = pedidoService.getPedidosByCliente(1);

        // Verificamos que el pedido tiene el cliente correcto
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getCliente().getId());
    }

    @Test
    void getPedidosByEstado() {
        Pedido pedido = new Pedido();
        pedido.setEstado("Enviado");

        when(pedidoJpaRepository.findByEstado("Enviado")).thenReturn(List.of(pedido));

        List<Pedido> result = pedidoService.getPedidosByEstado("Enviado");

        // Verificamos que el estado sea correcto
        assertEquals(1, result.size());
        assertEquals("Enviado", result.get(0).getEstado());
    }

    @Test
    void updateEstadoPedido() {
        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setEstado("En proceso");

        when(pedidoJpaRepository.findById(1)).thenReturn(Optional.of(pedido));
        when(pedidoJpaRepository.save(pedido)).thenReturn(pedido);

        Pedido updated = pedidoService.updateEstadoPedido(1, "Enviado");

        // Verificamos que el estado haya cambiado
        assertNotNull(updated);
        assertEquals("Enviado", updated.getEstado());
    }
}
