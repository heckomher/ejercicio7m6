package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}
