package com.example.perfulandiaspa.services;

import com.example.perfulandiaspa.model.Sucursal;
import com.example.perfulandiaspa.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> getAllSucursales() {
        return sucursalRepository.findAll();
    }

    public Sucursal getSucursalById(int id) {
        return sucursalRepository.findById(id);
    }

    public Sucursal createSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public Sucursal updateSucursal(Sucursal sucursal) {
        return sucursalRepository.update(sucursal);
    }

    public boolean deleteSucursal(int id) {
        return sucursalRepository.delete(id);
    }

    public List<Sucursal> getSucursalesByCiudad(String ciudad) {
        return sucursalRepository.findByCiudad(ciudad);
    }
}