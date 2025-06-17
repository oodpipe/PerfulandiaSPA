package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Pedido;
import com.example.perfulandiaspa.jparepository.PedidoJpaRepository;
import com.example.perfulandiaspa.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoJpaRepository pedidoJpaRepository;

    // Inyección por constructor (recomendada)
    public PedidoService(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    // Obtener todos los pedidos
    public List<Pedido> getAllPedidos() {
        return pedidoJpaRepository.findAll();
    }

    // Obtener un pedido por ID
    public Pedido getPedidoById(int id) {
        return pedidoJpaRepository.findById(id).orElse(null);
    }

    // Crear un nuevo pedido (calculando el total)
    public Pedido createPedido(Pedido pedido) {
        double total = pedido.getProductos().stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
        pedido.setTotal(total);
        return pedidoJpaRepository.save(pedido);
    }

    // Actualizar un pedido (JPA actualiza si el ID ya existe)
    public Pedido updatePedido(Pedido pedido) {
        return pedidoJpaRepository.save(pedido);
    }

    // Eliminar pedido por ID
    public boolean deletePedido(int id) {
        if (pedidoJpaRepository.existsById(id)) {
            pedidoJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar pedidos por usuario
    public List<Pedido> getPedidosByCliente(int clienteId) {
        return pedidoJpaRepository.findByCliente_Id((clienteId));
    }

    // Buscar pedidos por estado
    public List<Pedido> getPedidosByEstado(String estado) {
        return pedidoJpaRepository.findByEstado(estado);
    }

    // Actualizar solo el estado del pedido
    public Pedido updateEstadoPedido(int id, String nuevoEstado) {
        Pedido pedido = getPedidoById(id);
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
            return pedidoJpaRepository.save(pedido);
        }
        return null;
    }
}