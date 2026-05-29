package com.example.inventario.backend.repository;

import com.example.inventario.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Spring Data JPA genera la consulta automáticamente a partir del nombre del método
    Optional<Usuario> findByEmail(String email);
}
