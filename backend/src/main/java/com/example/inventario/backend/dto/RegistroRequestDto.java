package com.example.inventario.backend.dto;

import com.example.inventario.backend.model.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO para recibir los datos al momento de registrar un nuevo usuario.
// Incluimos el rol por simplicidad académica:
// en producción, el rol se asignaría internamente (ej: COMPRADOR por defecto).
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroRequestDto {
    private String nombre;
    private String email;
    private String password;
    private RolUsuario rol; // Se acepta en el request para facilitar las pruebas
}
