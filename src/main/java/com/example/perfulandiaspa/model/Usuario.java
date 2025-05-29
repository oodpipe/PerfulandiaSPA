package com.example.perfulandiaspa.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String rol; // Admin, Gerente, Empleado, Logística
    private String sucursalAsignada; // Para gerentes/empleados
}