package com.example.inventario.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // El email funciona como nombre de usuario único
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // Contraseña en texto plano (válido para 4to semestre; en producción usaría BCrypt)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nombre")
    private String nombre;

    // Guardamos el rol como String para que sea legible en la BD
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RolUsuario rol;
}
