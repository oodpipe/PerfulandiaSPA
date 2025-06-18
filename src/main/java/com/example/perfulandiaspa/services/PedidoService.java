package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.jparepository.PedidoJpaRepository;
import com.example.perfulandiaspa.jparepository.ProductoJpaRepository;
import com.example.perfulandiaspa.model.Pedido;
import com.example.perfulandiaspa.model.Producto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PedidoService {

    private final PedidoJpaRepository pedidoJpaRepository;
    private final ProductoJpaRepository productoJpaRepository;

    public PedidoService(PedidoJpaRepository pedidoJpaRepository, ProductoJpaRepository productoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
        this.productoJpaRepository = productoJpaRepository;
    }

    // Obtener todos los pedidos
    public List<Pedido> getAllPedidos() {
        return pedidoJpaRepository.findAll();
    }

    // Obtener un pedido por ID
    public Pedido getPedidoById(int id) {
        return pedidoJpaRepository.findById(id).orElse(null);
    }

    // Crear un nuevo pedido, cargando productos reales desde BD y calculando total
    public Pedido createPedido(Pedido pedido) {
        cargarProductosYCalcularTotal(pedido);
        return pedidoJpaRepository.save(pedido);
    }

    // Actualizar un pedido completo
    public Pedido updatePedido(Pedido pedido) {
        return pedidoJpaRepository.save(pedido);
    }

    // Eliminar un pedido por ID
    public boolean deletePedido(int id) {
        if (pedidoJpaRepository.existsById(id)) {
            pedidoJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar pedidos por ID de cliente
    public List<Pedido> getPedidosByCliente(int clienteId) {
        return pedidoJpaRepository.findByCliente_Id(clienteId);
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

    // Método reutilizable: carga productos reales desde la base de datos y calcula el total
    private void cargarProductosYCalcularTotal(Pedido pedido) {
        List<Producto> productos = pedido.getProductos().stream()
                .map(p -> productoJpaRepository.findById(p.getId()).orElse(null))
                .filter(Objects::nonNull)
                .toList();

        double total = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        pedido.setProductos(productos);
        pedido.setTotal(total);
    }
}
