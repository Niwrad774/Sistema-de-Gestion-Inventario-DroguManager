package com.example.inventario.backend.dto;

import com.example.inventario.backend.model.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO que devuelve el servidor tras un login exitoso.
// Devolvemos el id, nombre y rol del usuario para que el frontend pueda
// saber qué pantallas mostrar según el rol.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private Long usuarioId;
    private String nombre;
    private String email;
    private RolUsuario rol;
}
