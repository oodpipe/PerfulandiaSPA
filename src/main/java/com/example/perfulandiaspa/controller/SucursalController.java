package com.example.perfulandiaspa.controller;

import com.example.perfulandiaspa.model.Sucursal;
import com.example.perfulandiaspa.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<Sucursal>> getAllSucursales() {
        return new ResponseEntity<>(sucursalService.getAllSucursales(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> getSucursalById(@PathVariable int id) {
        Sucursal sucursal = sucursalService.getSucursalById(id);
        if(sucursal != null) {
            return new ResponseEntity<>(sucursal, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<Sucursal>> getSucursalesByCiudad(@PathVariable String ciudad) {
        return new ResponseEntity<>(sucursalService.getSucursalesByCiudad(ciudad), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Sucursal> createSucursal(@RequestBody Sucursal sucursal) {
        return new ResponseEntity<>(sucursalService.createSucursal(sucursal), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> updateSucursal(@PathVariable int id, @RequestBody Sucursal sucursal) {
        sucursal.setId(id);
        Sucursal updated = sucursalService.updateSucursal(sucursal);
        if(updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable int id) {
        if(sucursalService.deleteSucursal(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}