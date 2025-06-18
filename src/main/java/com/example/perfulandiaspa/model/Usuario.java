package com.example.perfulandiaspa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String email;
    private String rol; // Admin, Gerente, Empleado, Logística

    // Relación con sucursal, solo para gerentes y empleados
    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
}