package com.biblioteca.biblioteca_api.repositories;

import com.biblioteca.biblioteca_api.entities.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
