package com.example.inventario.backend.service;

import com.example.inventario.backend.dto.LoginRequestDto;
import com.example.inventario.backend.dto.LoginResponseDto;
import com.example.inventario.backend.dto.RegistroRequestDto;
import com.example.inventario.backend.dto.RegistroResponseDto;

public interface UsuarioService {
    // Valida credenciales y devuelve los datos del usuario con su rol
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    // Registra un nuevo usuario; lanza excepción si el email ya existe
    RegistroResponseDto registrarUsuario(RegistroRequestDto registroRequestDto);
}
