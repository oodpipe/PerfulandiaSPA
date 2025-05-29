package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Producto;
import com.example.perfulandiaspa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(int id) {
        return productoRepository.findById(id);
    }

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Producto producto) {
        return productoRepository.update(producto);
    }

    public boolean deleteProducto(int id) {
        return productoRepository.delete(id);
    }
}