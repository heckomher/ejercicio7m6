package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByNombreUsuarioIgnoreCase(String nombreUsuario);

}
