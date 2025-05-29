package com.example.perfulandiaspa.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {
    private int id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String horarioApertura;
    private String horarioCierre;
}