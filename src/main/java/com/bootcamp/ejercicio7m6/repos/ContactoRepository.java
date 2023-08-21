package com.bootcamp.ejercicio7m6.repos;

import com.bootcamp.ejercicio7m6.domain.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactoRepository extends JpaRepository<Contacto, Integer> {
}
