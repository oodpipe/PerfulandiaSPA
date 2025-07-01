package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.HistorialCliente;
import com.example.perfulandiaspa.services.HistorialClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/historiales")
public class HistorialClienteController {

    private final HistorialClienteService historialService;

    public HistorialClienteController(HistorialClienteService historialService) {
        this.historialService = historialService;
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<HistorialCliente> obtenerPorClienteId(@PathVariable Long clienteId) {
        return historialService.buscarPorClienteId(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/alergias")
    public ResponseEntity<Void> actualizarAlergias(
            @PathVariable Long id,
            @RequestParam String alergias) {
        historialService.actualizarAlergias(id, alergias);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{clienteId}/visitas")
    public ResponseEntity<Void> agregarVisita(
            @PathVariable Long clienteId,
            @RequestParam String visita) {
        historialService.agregarVisita(clienteId, visita);
        return ResponseEntity.noContent().build();
    }
}