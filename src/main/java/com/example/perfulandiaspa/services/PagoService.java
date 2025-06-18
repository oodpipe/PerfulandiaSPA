package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.jparepository.PagoJpaRepository;
import com.example.perfulandiaspa.jparepository.ProductoJpaRepository;
import com.example.perfulandiaspa.model.Pago;
import com.example.perfulandiaspa.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {

    private final PagoJpaRepository pagoJpaRepository;
    private final ProductoJpaRepository productoJpaRepository;

    // Inyección de dependencias por constructor
    public PagoService(PagoJpaRepository pagoJpaRepository, ProductoJpaRepository productoJpaRepository) {
        this.pagoJpaRepository = pagoJpaRepository;
        this.productoJpaRepository = productoJpaRepository;
    }

    // Obtener todos los pagos
    public List<Pago> getAllPagos() {
        return pagoJpaRepository.findAll();
    }

    // Obtener un pago por ID
    public Pago getPagoById(int id) {
        return pagoJpaRepository.findById(id).orElse(null);
    }

    // Crear nuevo pago (cargando productos reales y calculando total)
    public Pago createPago(Pago pago) {
        // Cargar desde base de datos los productos incluidos
        List<Producto> productosCargados = pago.getProductos().stream()
                .map(p -> productoJpaRepository.findById(p.getId()).orElse(null))
                .filter(p -> p != null)
                .toList();

        // Calcular total del pago
        double total = productosCargados.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        pago.setProductos(productosCargados);
        pago.setTotal(total);

        return pagoJpaRepository.save(pago);
    }

    // Actualizar un pago existente
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

    // Obtener pagos por estado
    public List<Pago> getPagosByEstado(String estado) {
        return pagoJpaRepository.findByEstado(estado);
    }

    // Obtener pagos por ID del cliente
    public List<Pago> getPagosByCliente(int clienteId) {
        return pagoJpaRepository.findByCliente_Id(clienteId);
    }
}