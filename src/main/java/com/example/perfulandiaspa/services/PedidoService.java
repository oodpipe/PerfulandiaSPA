package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Pedido;
import com.example.perfulandiaspa.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedidoById(int id) {
        return pedidoRepository.findById(id);
    }

    public Pedido createPedido(Pedido pedido) {
        // Calcular el total del pedido
        double total = pedido.getProductos().stream()
                .mapToDouble(p -> p.getPrecio())
                .sum();
        pedido.setTotal(total);
        return pedidoRepository.save(pedido);
    }

    public Pedido updatePedido(Pedido pedido) {
        return pedidoRepository.update(pedido);
    }

    public boolean deletePedido(int id) {
        return pedidoRepository.delete(id);
    }

    public List<Pedido> getPedidosByUsuario(int usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public List<Pedido> getPedidosByEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public Pedido updateEstadoPedido(int id, String nuevoEstado) {
        return pedidoRepository.updateEstado(id, nuevoEstado);
    }
}