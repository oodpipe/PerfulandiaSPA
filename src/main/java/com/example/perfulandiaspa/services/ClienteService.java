package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Cliente;
import com.example.perfulandiaspa.jparepository.ClienteJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteJpaRepository clienteRepository;

    public ClienteService(ClienteJpaRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorIdConHistorial(Long id) {
        return clienteRepository.findByIdWithHistorial(id);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNombre(String termino) {
        return clienteRepository.buscarPorNombre(termino);
    }

    @Transactional
    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombre(clienteActualizado.getNombre());
                    cliente.setEmail(clienteActualizado.getEmail());
                    cliente.setDireccion(clienteActualizado.getDireccion());
                    cliente.setTelefono(clienteActualizado.getTelefono());
                    return clienteRepository.save(cliente);
                }).orElse(null);
    }

    @Transactional
    public boolean eliminarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}