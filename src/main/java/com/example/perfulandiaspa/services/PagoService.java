package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Pago;
import com.example.perfulandiaspa.repository.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {
    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> getAllPagos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> getPagoById(int id) {
        return pagoRepository.findById(id);
    }

    public void addPago(Pago pago) {
        pagoRepository.save(pago);
    }

    public void deletePago(int id) {
        pagoRepository.deleteById(id);
    }
}