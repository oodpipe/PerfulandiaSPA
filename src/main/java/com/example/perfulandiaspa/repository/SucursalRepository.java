package com.example.perfulandiaspa.repository;

import com.example.perfulandiaspa.model.Sucursal;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SucursalRepository {
    private List<Sucursal> sucursales = new ArrayList<>();

    public List<Sucursal> findAll() {
        return sucursales;
    }

    public Sucursal findById(int id) {
        return sucursales.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Sucursal save(Sucursal sucursal) {
        if(sucursal.getId() == 0) {
            int maxId = sucursales.stream()
                    .mapToInt(Sucursal::getId)
                    .max()
                    .orElse(0);
            sucursal.setId(maxId + 1);
        }
        sucursales.add(sucursal);
        return sucursal;
    }

    public Sucursal update(Sucursal sucursal) {
        Sucursal existente = findById(sucursal.getId());
        if(existente != null) {
            existente.setNombre(sucursal.getNombre());
            existente.setDireccion(sucursal.getDireccion());
            existente.setCiudad(sucursal.getCiudad());
            existente.setHorarioApertura(sucursal.getHorarioApertura());
            existente.setHorarioCierre(sucursal.getHorarioCierre());
        }
        return existente;
    }

    public boolean delete(int id) {
        return sucursales.removeIf(s -> s.getId() == id);
    }

    public List<Sucursal> findByCiudad(String ciudad) {
        return sucursales.stream()
                .filter(s -> s.getCiudad().equalsIgnoreCase(ciudad))
                .toList();
    }
}