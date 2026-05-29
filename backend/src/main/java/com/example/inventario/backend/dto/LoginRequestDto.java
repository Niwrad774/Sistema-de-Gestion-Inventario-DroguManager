package com.example.inventario.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTO para recibir las credenciales en el endpoint de login
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    private String email;
    private String password;
}
