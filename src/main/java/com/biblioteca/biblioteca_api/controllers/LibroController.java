package com.biblioteca.biblioteca_api.controllers;

import com.biblioteca.biblioteca_api.entities.Libro;
import com.biblioteca.biblioteca_api.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PatchMapping("/{id}")
    public Libro updateLibroParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        return libroRepository.findById(id).map(libro -> {
            campos.forEach((key, value) -> {
                switch (key) {
                    case "nombre" -> libro.setNombre((String) value);
                    case "autor" -> libro.setAutor((String) value);
                    case "estrellas" -> libro.setEstrellas((Double) value);
                    case "fechaPublicacion" -> libro.setFechaPublicacion((String) value);
                    case "urlFoto" -> libro.setUrlFoto((String) value);
                    case "editorial" -> libro.setEditorial((String) value);
                    case "genero" -> libro.setGenero((String) value);
                    case "cantPag" -> libro.setCantPag((int) value);
                    case "idioma" -> libro.setIdioma((String) value);
                }
            });
            return libroRepository.save(libro);
        }).orElse(null);
    }

    // Eliminar un libro
    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id) {
        libroRepository.deleteById(id);
    }
}
