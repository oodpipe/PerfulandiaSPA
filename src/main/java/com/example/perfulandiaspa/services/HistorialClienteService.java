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

    @Transactional
    public HistorialCliente guardarHistorial(HistorialCliente historial) {
        return historialRepository.save(historial);
    }

    @Transactional(readOnly = true)
    public Optional<HistorialCliente> buscarPorClienteId(Long clienteId) {
        return historialRepository.findByClienteId(clienteId);
    }

    @Transactional
    public void actualizarAlergias(Long id, String alergias) {
        historialRepository.actualizarAlergias(id, alergias);
    }

    @Transactional
    public void agregarVisita(Long clienteId, String visita) {
        historialRepository.findByClienteId(clienteId).ifPresent(historial -> {
            historial.getVisitas().add(visita);
            historialRepository.save(historial);
        });
    }
}