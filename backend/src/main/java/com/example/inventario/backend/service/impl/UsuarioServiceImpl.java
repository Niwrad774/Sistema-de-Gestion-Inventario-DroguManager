package com.example.inventario.backend.service.impl;

import com.example.inventario.backend.dto.LoginRequestDto;
import com.example.inventario.backend.dto.LoginResponseDto;
import com.example.inventario.backend.exception.ResourceNotFoundException;
import com.example.inventario.backend.model.Usuario;
import com.example.inventario.backend.repository.UsuarioRepository;
import com.example.inventario.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        // 1. Buscar al usuario por su email
        Usuario usuario = usuarioRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con email: " + loginRequestDto.getEmail()));

        // 2. Comparar la contraseña ingresada con la guardada en la BD
        //    (En producción, esto se haría con BCrypt: passwordEncoder.matches(...))
        if (!usuario.getPassword().equals(loginRequestDto.getPassword())) {
            // Lanzamos una excepción simple. En producción usaríamos BadCredentialsException de Spring Security.
            throw new IllegalArgumentException("Contraseña incorrecta.");
        }

        // 3. Si todo es correcto, devolvemos los datos del usuario con su rol
        return new LoginResponseDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol()
        );
    }
}
