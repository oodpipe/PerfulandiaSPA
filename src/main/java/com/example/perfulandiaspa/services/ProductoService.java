package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Producto;
import com.example.perfulandiaspa.jparepository.ProductoJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoJpaRepository productoJpaRepository;

    // Inyección por constructor (recomendado por Spring)
    public ProductoService(ProductoJpaRepository productoJpaRepository) {
        this.productoJpaRepository = productoJpaRepository;
    }

    // Obtener todos los productos
    public List<Producto> getAllProductos() {
        return productoJpaRepository.findAll();
    }

    // Buscar producto por ID
    public Producto getProductoById(int id) {
        return productoJpaRepository.findById(id).orElse(null);
    }

    // Crear nuevo producto
    public Producto createProducto(Producto producto) {
        return productoJpaRepository.save(producto);
    }

    // Actualizar producto existente (JPA lo actualiza si el ID ya existe)
    public Producto updateProducto(Producto producto) {
        return productoJpaRepository.save(producto);
    }

    // Eliminar producto por ID (solo si existe)
    public boolean deleteProducto(int id) {
        if (productoJpaRepository.existsById(id)) {
            productoJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}