package com.biblioteca.biblioteca_api.controllers;

import com.biblioteca.biblioteca_api.entities.Libro;
import com.biblioteca.biblioteca_api.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    // Obtener todos los libros
    @GetMapping
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    // Obtener un libro por id
    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    // Crear un nuevo libro
    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    // Actualizar un libro
    @PutMapping("/{id}")
    public Libro updateLibro(@PathVariable Long id, @RequestBody Libro libro) {
        if (libroRepository.existsById(id)) {
            libro.setId(id);
            return libroRepository.save(libro);
        }
        return null;
    }

    // Eliminar un libro
    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }
}
