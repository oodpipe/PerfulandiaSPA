package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Cliente;
import com.example.perfulandiaspa.jparepository.ClienteJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteJpaRepository clienteJpaRepository;

    // Inyección del repositorio por constructor
    public ClienteService(ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }

    // Obtener todos los clientes
    public List<Cliente> getAllClientes() {
        return clienteJpaRepository.findAll();
    }

    // Obtener un cliente por su ID
    public Cliente getClienteById(int id) {
        return clienteJpaRepository.findById(id).orElse(null);
    }

    // Obtener clientes por ID de sucursal
    public List<Cliente> getClientesBySucursalId(int sucursalId) {
        return clienteJpaRepository.findBySucursal_Id(sucursalId);
    }

    // Crear un nuevo cliente
    public Cliente createCliente(Cliente cliente) {
        return clienteJpaRepository.save(cliente);
    }

    // Actualizar un cliente existente
    public Cliente updateCliente(Cliente cliente) {
        return clienteJpaRepository.save(cliente);
    }

    // Eliminar cliente por ID
    public boolean deleteCliente(int id) {
        if (clienteJpaRepository.existsById(id)) {
            clienteJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}