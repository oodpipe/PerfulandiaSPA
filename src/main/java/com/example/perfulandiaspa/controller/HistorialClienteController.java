package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.HistorialCliente;
import com.example.perfulandiaspa.services.HistorialClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/historial-perfumeria")
public class HistorialClienteController {

    private final HistorialClienteService historialService;

    public HistorialClienteController(HistorialClienteService historialService) {
        this.historialService = historialService;
    }

    // Endpoint para registrar compra
    @PostMapping("/{clienteId}/compras")
    public ResponseEntity<Void> registrarCompra(
            @PathVariable Long clienteId,
            @RequestParam String detalleCompra) {
        historialService.registrarCompra(clienteId, detalleCompra);
        return ResponseEntity.ok().build();
    }

    // Endpoint para obtener historial
    @GetMapping("/{clienteId}")
    public ResponseEntity<HistorialCliente> obtenerHistorial(
            @PathVariable Long clienteId) {
        return historialService.obtenerHistorial(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}