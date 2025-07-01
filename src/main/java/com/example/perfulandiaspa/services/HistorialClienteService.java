package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.HistorialCliente;
import com.example.perfulandiaspa.jparepository.HistorialClienteJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class HistorialClienteService {

    private final HistorialClienteJpaRepository historialRepository;

    public HistorialClienteService(HistorialClienteJpaRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    // Método para registrar compras
    @Transactional
    public void registrarCompra(Long clienteId, String detalleCompra) {
        historialRepository.findByClienteId(clienteId).ifPresent(historial -> {
            historial.getCompras().add(detalleCompra);
            historialRepository.save(historial);
        });
    }

    // Método para actualizar preferencias
    @Transactional
    public void actualizarPreferencias(Long clienteId, String preferencias) {
        historialRepository.findByClienteId(clienteId).ifPresent(historial -> {
            historial.setPreferenciasFragancias(preferencias);
            historialRepository.save(historial);
        });
    }

    // Método para consultar historial
    @Transactional(readOnly = true)
    public Optional<HistorialCliente> obtenerHistorial(Long clienteId) {
        return historialRepository.findByClienteId(clienteId);
    }
}