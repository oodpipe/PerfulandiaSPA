package com.example.perfulandiaspa.repository;

import com.example.perfulandiaspa.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepository {

    private final List<Pedido> pedidos = new ArrayList<>();

    // Obtener todos los pedidos
    public List<Pedido> findAll() {
        return pedidos;
    }

    // Buscar un pedido por su ID
    public Pedido findById(int id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Guardar un nuevo pedido
    public Pedido save(Pedido pedido) {
        if (pedido.getId() == 0) {
            int nuevoId = pedidos.size() + 1;
            pedido.setId(nuevoId);
        }
        pedidos.add(pedido);
        return pedido;
    }

    // Actualizar los datos de un pedido existente
    public Pedido update(Pedido pedido) {
        Pedido actual = findById(pedido.getId());
        if (actual != null) {
            actual.setCliente(pedido.getCliente());
            actual.setFecha(pedido.getFecha());
            actual.setEstado(pedido.getEstado());
            actual.setProductos(pedido.getProductos());
            actual.setTotal(pedido.getTotal());
        }
        return actual;
    }

    // Eliminar un pedido por ID
    public boolean delete(int id) {
        Pedido pedido = findById(id);
        if (pedido != null) {
            pedidos.remove(pedido);
            return true;
        }
        return false;
    }

    // Buscar pedidos por ID de cliente
    public List<Pedido> findByClienteId(int clienteId) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getCliente() != null && p.getCliente().getId() == clienteId) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // Buscar pedidos por estado (ej. "En proceso")
    public List<Pedido> findByEstado(String estado) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getEstado().equalsIgnoreCase(estado)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // Actualizar solo el estado del pedido
    public Pedido updateEstado(int id, String nuevoEstado) {
        Pedido pedido = findById(id);
        if (pedido != null) {
            pedido.setEstado(nuevoEstado);
        }
        return pedido;
    }
}
