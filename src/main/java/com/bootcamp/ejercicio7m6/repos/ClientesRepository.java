package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.domain.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientesRepository extends JpaRepository<Clientes, Long> {

    boolean existsByUsuarioIdUsuario(Long idUsuario);

}
