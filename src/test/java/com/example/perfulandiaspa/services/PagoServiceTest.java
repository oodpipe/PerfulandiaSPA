package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.jparepository.PagoJpaRepository;
import com.example.perfulandiaspa.jparepository.ProductoJpaRepository;
import com.example.perfulandiaspa.model.Cliente;
import com.example.perfulandiaspa.model.Pago;
import com.example.perfulandiaspa.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagoServiceTest {

    private PagoJpaRepository pagoJpaRepository;
    private ProductoJpaRepository productoJpaRepository;
    private PagoService pagoService;

    @BeforeEach
    void setUp() {
        // Mockeo de los repositorios
        pagoJpaRepository = mock(PagoJpaRepository.class);
        productoJpaRepository = mock(ProductoJpaRepository.class);

        // Inyectar mocks al servicio
        pagoService = new PagoService(pagoJpaRepository, productoJpaRepository);
    }

    @Test
    void getAllPagos() {
        // Datos de prueba
        Pago pago1 = new Pago();
        Pago pago2 = new Pago();

        when(pagoJpaRepository.findAll()).thenReturn(Arrays.asList(pago1, pago2));

        List<Pago> pagos = pagoService.getAllPagos();

        assertEquals(2, pagos.size());
        verify(pagoJpaRepository, times(1)).findAll(); // verifica que se llamó una vez
    }

    @Test
    void getPagoById() {
        Pago pago = new Pago();
        pago.setId(1);

        when(pagoJpaRepository.findById(1)).thenReturn(Optional.of(pago));

        Pago result = pagoService.getPagoById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void createPago() {
        Producto p1 = new Producto(1, "Perfume A", "Floral", 19990, 10, "Perfume");
        Producto p2 = new Producto(2, "Perfume B", "Amaderado", 24990, 5, "Perfume");

        when(productoJpaRepository.findById(1)).thenReturn(Optional.of(p1));
        when(productoJpaRepository.findById(2)).thenReturn(Optional.of(p2));

        Pago pago = new Pago();
        pago.setProductos(Arrays.asList(p1, p2));

        when(pagoJpaRepository.save(Mockito.any(Pago.class))).thenAnswer(i -> i.getArguments()[0]);

        Pago result = pagoService.createPago(pago);

        assertEquals(44980, result.getTotal());
        assertEquals(2, result.getProductos().size());
    }

    @Test
    void updatePago() {
        Pago pago = new Pago();
        pago.setId(1);
        pago.setEstado("pagado");

        when(pagoJpaRepository.save(pago)).thenReturn(pago);

        Pago result = pagoService.updatePago(pago);

        assertNotNull(result);
        assertEquals("pagado", result.getEstado());
    }

    @Test
    void deletePago() {
        when(pagoJpaRepository.existsById(1)).thenReturn(true);

        boolean result = pagoService.deletePago(1);

        assertTrue(result);
        verify(pagoJpaRepository).deleteById(1);
    }

    @Test
    void getPagosByEstado() {
        Pago pago = new Pago();
        pago.setEstado("por pagar");

        when(pagoJpaRepository.findByEstado("por pagar")).thenReturn(List.of(pago));

        List<Pago> result = pagoService.getPagosByEstado("por pagar");

        assertEquals(1, result.size());
        assertEquals("por pagar", result.get(0).getEstado());
    }

    @Test
    void getPagosByCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1);

        Pago pago = new Pago();
        pago.setCliente(cliente);

        when(pagoJpaRepository.findByCliente_Id(1)).thenReturn(List.of(pago));

        List<Pago> result = pagoService.getPagosByCliente(1);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getCliente().getId());
    }
}
