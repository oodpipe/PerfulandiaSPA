package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Sucursal;
import com.example.perfulandiaspa.jparepository.SucursalJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SucursalServiceTest {

    private SucursalJpaRepository sucursalJpaRepository;
    private SucursalService sucursalService;

    @BeforeEach
    void setUp() {
        // Mockeamos el repositorio de Sucursal
        sucursalJpaRepository = mock(SucursalJpaRepository.class);

        // Inyectamos el mock en el servicio
        sucursalService = new SucursalService(sucursalJpaRepository);
    }

    @Test
    void getAllSucursales() {
        // Datos de prueba con calles de Santiago de Chile
        Sucursal sucursal1 = new Sucursal(1, "Sucursal Providencia", "Av. Providencia 555", "Providencia", "09:00", "18:00");
        Sucursal sucursal2 = new Sucursal(2, "Sucursal Las Condes", "Av. Apoquindo 3000", "Las Condes", "10:00", "19:00");

        when(sucursalJpaRepository.findAll()).thenReturn(List.of(sucursal1, sucursal2));

        List<Sucursal> sucursales = sucursalService.getAllSucursales();

        // Verificamos que se obtienen las sucursales correctamente
        assertEquals(2, sucursales.size());
        verify(sucursalJpaRepository, times(1)).findAll(); // Verifica que findAll se haya llamado una vez
    }

    @Test
    void getSucursalById() {
        // Datos de prueba
        Sucursal sucursal = new Sucursal(1, "Sucursal Providencia", "Av. Providencia 555", "Providencia", "09:00", "18:00");

        when(sucursalJpaRepository.findById(1)).thenReturn(java.util.Optional.of(sucursal));

        Sucursal result = sucursalService.getSucursalById(1);

        // Verificamos que la sucursal se haya obtenido correctamente
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void createSucursal() {
        // Datos de prueba con calle de Santiago
        Sucursal sucursal = new Sucursal(1, "Sucursal Providencia", "Av. Providencia 555", "Providencia", "09:00", "18:00");

        when(sucursalJpaRepository.save(Mockito.any(Sucursal.class))).thenReturn(sucursal);

        Sucursal result = sucursalService.createSucursal(sucursal);

        // Verificamos que la sucursal se haya guardado correctamente
        assertNotNull(result);
        assertEquals("Sucursal Providencia", result.getNombre());
    }

    @Test
    void updateSucursal() {
        // Datos de prueba con calle de Santiago
        Sucursal sucursal = new Sucursal(1, "Sucursal Providencia", "Av. Providencia 555", "Providencia", "09:00", "18:00");

        when(sucursalJpaRepository.save(sucursal)).thenReturn(sucursal);

        Sucursal result = sucursalService.updateSucursal(sucursal);

        // Verificamos que la sucursal haya sido actualizada correctamente
        assertNotNull(result);
        assertEquals("Sucursal Providencia", result.getNombre());
    }

    @Test
    void deleteSucursal() {
        // Simulamos que la sucursal existe
        when(sucursalJpaRepository.existsById(1)).thenReturn(true);

        boolean result = sucursalService.deleteSucursal(1);

        // Verificamos que la sucursal haya sido eliminada correctamente
        assertTrue(result);
        verify(sucursalJpaRepository).deleteById(1); // Verifica que deleteById se haya llamado correctamente
    }

    @Test
    void getSucursalesByCiudad() {
        // Datos de prueba
        Sucursal sucursal = new Sucursal(1, "Sucursal Providencia", "Av. Providencia 555", "Providencia", "09:00", "18:00");

        // Simulamos el comportamiento del repositorio
        List<Sucursal> sucursales = new ArrayList<>();
        sucursales.add(sucursal);

        when(sucursalJpaRepository.findByCiudad("Providencia")).thenReturn(sucursales);

        List<Sucursal> result = sucursalService.getSucursalesByCiudad("Providencia");

        // Verificamos que la sucursal esté en la ciudad correcta
        assertEquals(1, result.size());
        assertEquals("Providencia", result.get(0).getCiudad());
    }
}