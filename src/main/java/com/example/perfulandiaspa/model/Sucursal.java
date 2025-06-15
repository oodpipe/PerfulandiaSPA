package com.example.perfulandiaspa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity // Marca esta clase como una tabla JPA
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    private int id;

    private String nombre;
    private String direccion;
    private String ciudad;
    private String horarioApertura;
    private String horarioCierre;
}