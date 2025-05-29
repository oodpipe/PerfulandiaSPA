package com.example.perfulandiaspa.repository;

import com.example.perfulandiaspa.model.Producto;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepository {
    private List<Producto> productos = new ArrayList<>();

    public List<Producto> findAll() {
        return productos;
    }

    public Producto findById(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public Producto save(Producto producto) {
        productos.add(producto);
        return producto;
    }

    public Producto update(Producto producto) {
        Producto existente = findById(producto.getId());
        if(existente != null) {
            existente.setNombre(producto.getNombre());
            existente.setDescripcion(producto.getDescripcion());
            existente.setPrecio(producto.getPrecio());
            existente.setStock(producto.getStock());
            existente.setCategoria(producto.getCategoria());
        }
        return existente;
    }

    public boolean delete(int id) {
        return productos.removeIf(p -> p.getId() == id);
    }
}