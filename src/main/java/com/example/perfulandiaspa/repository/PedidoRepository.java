package com.example.perfulandiaspa.repository;

import com.example.perfulandiaspa.model.Pedido;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PedidoRepository {
    private List<Pedido> pedidos = new ArrayList<>();

    public List<Pedido> findAll() {
        return pedidos;
    }

    public Pedido findById(int id) {
        return pedidos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Pedido save(Pedido pedido) {
        if(pedido.getId() == 0) {
            int maxId = pedidos.stream()
                    .mapToInt(Pedido::getId)
                    .max()
                    .orElse(0);
            pedido.setId(maxId + 1);
        }
        pedidos.add(pedido);
        return pedido;
    }

    public Pedido update(Pedido pedido) {
        Pedido existente = findById(pedido.getId());
        if(existente != null) {
            existente.setUsuarioId(pedido.getUsuarioId());
            existente.setFecha(pedido.getFecha());
            existente.setEstado(pedido.getEstado());
            existente.setProductos(pedido.getProductos());
            existente.setTotal(pedido.getTotal());
        }
        return existente;
    }

    public boolean delete(int id) {
        return pedidos.removeIf(p -> p.getId() == id);
    }

    public List<Pedido> findByUsuarioId(int usuarioId) {
        return pedidos.stream()
                .filter(p -> p.getUsuarioId() == usuarioId)
                .collect(Collectors.toList());
    }

    public List<Pedido> findByEstado(String estado) {
        return pedidos.stream()
                .filter(p -> p.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }

    public Pedido updateEstado(int id, String nuevoEstado) {
        Pedido pedido = findById(id);
        if(pedido != null) {
            pedido.setEstado(nuevoEstado);
        }
        return pedido;
    }
}