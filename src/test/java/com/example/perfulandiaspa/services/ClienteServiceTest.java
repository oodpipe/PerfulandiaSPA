package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.jparepository.ClienteJpaRepository;
import com.example.perfulandiaspa.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {

    private ClienteService service;
    private ClienteJpaRepository repo;

    // Configuración antes de cada prueba: se crea un mock del repositorio y se inyecta en el servicio
    @BeforeEach
    void setup() {
        repo = mock(ClienteJpaRepository.class);
        service = new ClienteService(repo);
    }

    // Prueba que verifica si se retornan todos los clientes correctamente
    @Test
    void getAllClientes() {
        when(repo.findAll()).thenReturn(List.of(new Cliente(), new Cliente()));
        assertEquals(2, service.getAllClientes().size());
    }

    // Prueba que verifica la búsqueda de un cliente por su ID
    @Test
    void getClienteById() {
        Cliente cliente = new Cliente();
        when(repo.findById(1)).thenReturn(Optional.of(cliente));
        assertEquals(cliente, service.getClienteById(1));
    }

    // Prueba que verifica la búsqueda de clientes por el ID de la sucursal
    @Test
    void getClientesBySucursalId() {
        when(repo.findBySucursal_Id(1)).thenReturn(List.of(new Cliente()));
        assertEquals(1, service.getClientesBySucursalId(1).size());
    }

    // Prueba para guardar un nuevo cliente
    @Test
    void createCliente() {
        Cliente cliente = new Cliente();
        when(repo.save(cliente)).thenReturn(cliente);
        assertEquals(cliente, service.createCliente(cliente));
    }

    // Prueba para actualizar un cliente existente
    @Test
    void updateCliente() {
        Cliente cliente = new Cliente();
        when(repo.save(cliente)).thenReturn(cliente);
        assertEquals(cliente, service.updateCliente(cliente));
    }

    // Prueba para eliminar un cliente por ID, devuelve true si existe
    @Test
    void deleteCliente() {
        when(repo.existsById(1)).thenReturn(true);
        assertTrue(service.deleteCliente(1));
    }
}