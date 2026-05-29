package com.example.inventario.backend.dto;

import com.example.inventario.backend.model.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO de respuesta al registrar un usuario.
// Nunca devolvemos el password en la respuesta.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroResponseDto {
    private Long id;
    private String nombre;
    private String email;
    private RolUsuario rol;
}
