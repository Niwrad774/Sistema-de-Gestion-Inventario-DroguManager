package com.example.inventario.backend.service.impl;

import com.example.inventario.backend.dto.LoginRequestDto;
import com.example.inventario.backend.dto.LoginResponseDto;
import com.example.inventario.backend.dto.RegistroRequestDto;
import com.example.inventario.backend.dto.RegistroResponseDto;
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

    @Override
    public RegistroResponseDto registrarUsuario(RegistroRequestDto registroRequestDto) {
        // 1. Verificar que no exista ya un usuario con ese email
        boolean emailEnUso = usuarioRepository.findByEmail(registroRequestDto.getEmail()).isPresent();
        if (emailEnUso) {
            // Lanzamos una excepción clara. El controlador devolverá un 400 Bad Request.
            throw new IllegalArgumentException("Ya existe un usuario registrado con el email: " + registroRequestDto.getEmail());
        }

        // 2. Mapear el DTO a la entidad Usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(registroRequestDto.getNombre());
        nuevoUsuario.setEmail(registroRequestDto.getEmail());
        nuevoUsuario.setPassword(registroRequestDto.getPassword()); // En producción: BCrypt
        nuevoUsuario.setRol(registroRequestDto.getRol());

        // 3. Guardar en la BD y devolver el DTO de respuesta (sin password)
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        return new RegistroResponseDto(
                usuarioGuardado.getId(),
                usuarioGuardado.getNombre(),
                usuarioGuardado.getEmail(),
                usuarioGuardado.getRol()
        );
    }
}
