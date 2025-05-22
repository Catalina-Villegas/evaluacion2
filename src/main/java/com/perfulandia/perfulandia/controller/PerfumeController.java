package com.perfulandia.perfulandia.controller;

import com.perfulandia.perfulandia.model.Perfume;
import com.perfulandia.perfulandia.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfumes")
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    // Crear un nuevo perfume
    @PostMapping
    public ResponseEntity<Perfume> crearPerfume(@RequestBody Perfume perfume) {
        Perfume nuevoPerfume = perfumeService.crearPerfume(perfume);
        return new ResponseEntity<>(nuevoPerfume, HttpStatus.CREATED);
    }

    // Obtener todos los perfumes
    @GetMapping
    public ResponseEntity<List<Perfume>> obtenerTodosLosPerfumes() {
        List<Perfume> perfumes = perfumeService.obtenerTodosLosPerfumes();
        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }

    // Obtener un perfume por ID
    @GetMapping("/id/{id}")
    public Perfume obtenerPerfumePorId(@PathVariable int id) {
        Perfume perfume = perfumeService.obtenerPerfumePorId(id);
        return perfume;
    }

    //Obtener un perfume por Marca
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<Perfume>> obtenerPerfumePorMarca(@PathVariable String marca) {
        List<Perfume> perfumes = perfumeService.obtenerPerfumePorMarca(marca);
        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }

    //Obtener un perfume por Genero
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Perfume>> obtenerPerfumePorGenero(@PathVariable String genero) {
        List<Perfume> perfumes = perfumeService.obtenerPerfumePorGenero(genero);
        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }


    // Actualizar un perfume existente
    @PutMapping("/{id}")
    public ResponseEntity<Perfume> actualizarPerfume(@PathVariable int id, @RequestBody Perfume perfume) {
        Perfume perfumeActualizado = perfumeService.actualizarPerfume(id, perfume);
        return (perfumeActualizado != null) ?
                new ResponseEntity<>(perfumeActualizado, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar un perfume por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPerfume(@PathVariable int id) {
        boolean eliminado = perfumeService.eliminarPerfume(id);
        return eliminado ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}