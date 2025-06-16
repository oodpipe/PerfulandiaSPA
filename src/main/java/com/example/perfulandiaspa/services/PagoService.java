package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Pago;
import com.example.perfulandiaspa.jparepository.PagoJpaRepository;
import com.example.perfulandiaspa.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {

    private final PagoJpaRepository pagoJpaRepository;

    // Inyección por constructor
    public PagoService(PagoJpaRepository pagoJpaRepository) {
        this.pagoJpaRepository = pagoJpaRepository;
    }

    // Obtener todos los pagos
    public List<Pago> getAllPagos() {
        return pagoJpaRepository.findAll();
    }

    // Obtener un pago por ID
    public Pago getPagoById(int id) {
        return pagoJpaRepository.findById(id).orElse(null);
    }

    // Crear nuevo pago (calculando el total según los productos)
    public Pago createPago(Pago pago) {
        double total = pago.getProductos().stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
        pago.setTotal(total);
        return pagoJpaRepository.save(pago);
    }

    // Actualizar pago existente
    public Pago updatePago(Pago pago) {
        return pagoJpaRepository.save(pago);
    }

    // Eliminar un pago por ID
    public boolean deletePago(int id) {
        if (pagoJpaRepository.existsById(id)) {
            pagoJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar pagos por estado (pagado o por pagar)
    public List<Pago> getPagosByEstado(String estado) {
        return pagoJpaRepository.findByEstado(estado);
    }

    // Buscar pagos por ID de cliente
    public List<Pago> getPagosByCliente(int clienteId) {
        return pagoJpaRepository.findByCliente_Id(clienteId);
    }
}