package com.example.inventario.backend.service;

import com.example.inventario.backend.dto.LoginRequestDto;
import com.example.inventario.backend.dto.LoginResponseDto;

public interface UsuarioService {
    // Valida credenciales y devuelve los datos del usuario con su rol
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
