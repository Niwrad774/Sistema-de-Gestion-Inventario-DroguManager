package com.example.inventario.backend.controller;

import com.example.inventario.backend.dto.LoginRequestDto;
import com.example.inventario.backend.dto.LoginResponseDto;
import com.example.inventario.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // POST /api/usuarios/login
    // Recibe email y contraseña, devuelve los datos del usuario con su rol
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto respuesta = usuarioService.login(loginRequestDto);
        return ResponseEntity.ok(respuesta);
    }
}
