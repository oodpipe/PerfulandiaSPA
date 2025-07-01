package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.jparepository.HistorialClienteJpaRepository;
import com.example.perfulandiaspa.model.Cliente;
import com.example.perfulandiaspa.model.HistorialCliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HistorialClienteServiceTest {

    private HistorialClienteJpaRepository historialJpaRepository;
    private HistorialClienteService historialService;

    @BeforeEach
    void setUp() {
        // Mockeamos el repositorio
        historialJpaRepository = mock(HistorialClienteJpaRepository.class);

        // Inyectamos el mock en el servicio
        historialService = new HistorialClienteService(historialJpaRepository);
    }

    @Test
    void getAllHistoriales() {
        // Datos de prueba
        HistorialCliente historial1 = new HistorialCliente();
        HistorialCliente historial2 = new HistorialCliente();

        when(historialJpaRepository.findAll()).thenReturn(Arrays.asList(historial1, historial2));

        List<HistorialCliente> historiales = historialService.getAllHistoriales();

        assertEquals(2, historiales.size());
        verify(historialJpaRepository, times(1)).findAll();
    }

    @Test
    void getHistorialById() {
        HistorialCliente historial = new HistorialCliente();
        historial.setId(1);

        when(historialJpaRepository.findById(1)).thenReturn(Optional.of(historial));

        HistorialCliente result = historialService.getHistorialById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void createHistorial() {
        HistorialCliente historial = new HistorialCliente();
        historial.setObservaciones("Primera visita");

        when(historialJpaRepository.save(Mockito.any(HistorialCliente.class))).thenReturn(historial);

        HistorialCliente result = historialService.createHistorial(historial);

        assertNotNull(result);
        assertEquals("Primera visita", result.getObservaciones());
    }

    @Test
    void updateHistorial() {
        HistorialCliente historial = new HistorialCliente();
        historial.setId(1);
        historial.setObservaciones("Visita actualizada");

        when(historialJpaRepository.save(historial)).thenReturn(historial);

        HistorialCliente result = historialService.updateHistorial(historial);

        assertNotNull(result);
        assertEquals("Visita actualizada", result.getObservaciones());
    }

    @Test
    void deleteHistorial() {
        when(historialJpaRepository.existsById(1)).thenReturn(true);

        boolean result = historialService.deleteHistorial(1);

        assertTrue(result);
        verify(historialJpaRepository).deleteById(1);
    }

    @Test
    void getHistorialesByCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1);

        HistorialCliente historial = new HistorialCliente();
        historial.setCliente(cliente);

        when(historialJpaRepository.findByCliente_Id(1)).thenReturn(List.of(historial));

        List<HistorialCliente> result = historialService.getHistorialesByCliente(1);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getCliente().getId());
    }

    @Test
    void getHistorialesByTipo() {
        HistorialCliente historial = new HistorialCliente();
        historial.setTipo("VISITA");

        when(historialJpaRepository.findByTipo("VISITA")).thenReturn(List.of(historial));

        List<HistorialCliente> result = historialService.getHistorialesByTipo("VISITA");

        assertEquals(1, result.size());
        assertEquals("VISITA", result.get(0).getTipo());
    }

    @Test
    void getHistorialesRecientes() {
        HistorialCliente historial1 = new HistorialCliente();
        HistorialCliente historial2 = new HistorialCliente();

        when(historialJpaRepository.findTop5ByOrderByFechaDesc()).thenReturn(Arrays.asList(historial1, historial2));

        List<HistorialCliente> result = historialService.getHistorialesRecientes();

        assertEquals(2, result.size());
        verify(historialJpaRepository).findTop5ByOrderByFechaDesc();
    }
}