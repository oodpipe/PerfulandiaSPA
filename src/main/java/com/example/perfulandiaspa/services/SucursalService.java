package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Sucursal;
import com.example.perfulandiaspa.jparepository.SucursalJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {

    private final SucursalJpaRepository sucursalJpaRepository;

    // Inyección por constructor (mejor práctica)
    public SucursalService(SucursalJpaRepository sucursalJpaRepository) {
        this.sucursalJpaRepository = sucursalJpaRepository;
    }

    // Obtener todas las sucursales
    public List<Sucursal> getAllSucursales() {
        return sucursalJpaRepository.findAll();
    }

    // Obtener una sucursal por ID
    public Sucursal getSucursalById(int id) {
        return sucursalJpaRepository.findById(id).orElse(null);
    }

    // Crear una nueva sucursal
    public Sucursal createSucursal(Sucursal sucursal) {
        return sucursalJpaRepository.save(sucursal);
    }

    // Actualizar una sucursal existente
    public Sucursal updateSucursal(Sucursal sucursal) {
        return sucursalJpaRepository.save(sucursal);
    }

    // Eliminar una sucursal por ID
    public boolean deleteSucursal(int id) {
        if (sucursalJpaRepository.existsById(id)) {
            sucursalJpaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Buscar sucursales por ciudad (requiere método en el JpaRepository)
    public List<Sucursal> getSucursalesByCiudad(String ciudad) {
        return sucursalJpaRepository.findByCiudad(ciudad);
    }
}