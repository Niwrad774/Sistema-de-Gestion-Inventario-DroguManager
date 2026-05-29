package com.example.inventario.backend.controller;

import com.example.inventario.backend.dto.LoginRequestDto;
import com.example.inventario.backend.dto.LoginResponseDto;
import com.example.inventario.backend.dto.RegistroRequestDto;
import com.example.inventario.backend.dto.RegistroResponseDto;
import com.example.inventario.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // permitir llamadas desde Vite dev server durante desarrollo
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

    // POST /api/usuarios/registro
    // Crea un nuevo usuario en el sistema
    @PostMapping("/registro")
    public ResponseEntity<RegistroResponseDto> registro(@RequestBody RegistroRequestDto registroRequestDto) {
        RegistroResponseDto respuesta = usuarioService.registrarUsuario(registroRequestDto);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }
}
