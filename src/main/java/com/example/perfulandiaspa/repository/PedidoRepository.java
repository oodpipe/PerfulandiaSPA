package com.example.perfulandiaspa.repository;

import com.example.perfulandiaspa.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepository {
    private List<Pedido> pedidos = new ArrayList<>();

    public List<Pedido> findAll() {
        return pedidos;
    }

    public Pedido findById(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Pedido save(Pedido pedido) {
        if (pedido.getId() == 0) {
            int nuevoId = pedidos.size() + 1;
            pedido.setId(nuevoId);
        }
        pedidos.add(pedido);
        return pedido;
    }

    public Pedido update(Pedido pedido) {
        Pedido actual = findById(pedido.getId());
        if (actual != null) {
            actual.setUsuario(pedido.getUsuario());
            actual.setFecha(pedido.getFecha());
            actual.setEstado(pedido.getEstado());
            actual.setProductos(pedido.getProductos());
            actual.setTotal(pedido.getTotal());
        }
        return actual;
    }

    public boolean delete(int id) {
        Pedido pedido = findById(id);
        if (pedido != null) {
            pedidos.remove(pedido);
            return true;
        }
        return false;
    }

    public List<Pedido> findByUsuarioId(int usuarioId) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getUsuario() != null && p.getUsuario().getId() == usuarioId) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Pedido> findByEstado(String estado) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public Pedido updateEstado(int id, String nuevoEstado) {
        Pedido pedido = findById(id);
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
        }
        return pedido;
    }
}
