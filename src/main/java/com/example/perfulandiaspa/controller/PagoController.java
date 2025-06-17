package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.Pago;
import com.example.perfulandiaspa.services.PagoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    // Obtener todos los pagos
    @GetMapping
    public List<Pago> getAllPagos() {
        return pagoService.getAllPagos();
    }

    // Obtener un pago por ID
    @GetMapping("/{id}")
    public Pago getPagoById(@PathVariable int id) {
        return pagoService.getPagoById(id);
    }

    // Crear un nuevo pago
    @PostMapping
    public Pago createPago(@RequestBody Pago pago) {
        return pagoService.createPago(pago);
    }

    // Eliminar un pago por ID
    @DeleteMapping("/{id}")
    public void deletePago(@PathVariable int id) {
        pagoService.deletePago(id);
    }

    // Obtener pagos por estado
    @GetMapping("/estado/{estado}")
    public List<Pago> getPagosByEstado(@PathVariable String estado) {
        return pagoService.getPagosByEstado(estado);
    }

    // Obtener pagos por cliente ID (corregido)
    @GetMapping("/cliente/{clienteId}")
    public List<Pago> getPagosByCliente(@PathVariable int clienteId) {
        return pagoService.getPagosByCliente(clienteId);
    }

    // Actualizar un pago
    @PutMapping
    public Pago updatePago(@RequestBody Pago pago) {
        return pagoService.updatePago(pago);
    }
}