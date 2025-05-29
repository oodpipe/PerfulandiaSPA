package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.Pago;
import com.example.perfulandiaspa.services.PagoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {
    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public List<Pago> getAllPagos() {
        return pagoService.getAllPagos();
    }

    @GetMapping("/{id}")
    public Optional<Pago> getPagoById(@PathVariable int id) {
        return pagoService.getPagoById(id);
    }

    @PostMapping
    public void addPago(@RequestBody Pago pago) {
        pagoService.addPago(pago);
    }

    @DeleteMapping("/{id}")
    public void deletePago(@PathVariable int id) {
        pagoService.deletePago(id);
    }
}

