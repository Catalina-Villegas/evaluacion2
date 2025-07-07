package com.perfulandia.perfulandia.controller;

import com.perfulandia.perfulandia.model.Perfume;
import com.perfulandia.perfulandia.service.PerfumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfumes")
@Tag(name="Perfumes", description="Operaciones relacionadas con los perfumes")
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    // Crear un nuevo perfume
    @PostMapping
    @Operation(summary = "Agregar perfume", description = "Se agrega un nuevo perfume")
    public ResponseEntity<Perfume> crearPerfume(@RequestBody Perfume perfume) {
        Perfume nuevoPerfume = perfumeService.crearPerfume(perfume);
        return new ResponseEntity<>(nuevoPerfume, HttpStatus.CREATED);
    }

    // Obtener todos los perfumes
    @GetMapping
    @Operation(summary = "Obtener todos los perfumes", description = "Obtiene una lista de todos los perfumes")
    public ResponseEntity<List<Perfume>> obtenerTodosLosPerfumes() {
        List<Perfume> perfumes = perfumeService.obtenerTodosLosPerfumes();
        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }

    // Obtener un perfume por ID
    @GetMapping("/id/{id}")
    @Operation(summary = "Obtener el perfume con el id indicado", description = "Obtiene el perfume con el ID ingresado")
    public Perfume obtenerPerfumePorId(@PathVariable int id) {
        Perfume perfume = perfumeService.obtenerPerfumePorId(id);
        return perfume;
    }

    //Obtener un perfume por Marca
    @GetMapping("/marca/{marca}")
    @Operation(summary = "Obtener todos perfumes con la marca indicada", description = "Obtiene una lista de todos los perfumes con la respectiva marca ingresada")
    public ResponseEntity<List<Perfume>> obtenerPerfumePorMarca(@PathVariable String marca) {
        List<Perfume> perfumes = perfumeService.obtenerPerfumePorMarca(marca);
        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }

    //Obtener un perfume por Genero
    @GetMapping("/genero/{genero}")
    @Operation(summary = "Obtener todos los perfumes con el género indicado", description = "Obtiene una lista de todos los perfumes con el respectivo género")
    public ResponseEntity<List<Perfume>> obtenerPerfumePorGenero(@PathVariable String genero) {
        List<Perfume> perfumes = perfumeService.obtenerPerfumePorGenero(genero);
        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }


    // Actualizar un perfume existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un perfume existente", description = "Actualiza un perfume existente ingresando el ID")
    public ResponseEntity<Perfume> actualizarPerfume(@PathVariable int id, @RequestBody Perfume perfume) {
        Perfume perfumeActualizado = perfumeService.actualizarPerfume(id, perfume);
        return (perfumeActualizado != null) ?
                new ResponseEntity<>(perfumeActualizado, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar un perfume por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un perfume existente", description = "Elimina un perfume existente ingresando el ID correspondiente")
    public ResponseEntity<Void> eliminarPerfume(@PathVariable int id) {
        boolean eliminado = perfumeService.eliminarPerfume(id);
        return eliminado ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}