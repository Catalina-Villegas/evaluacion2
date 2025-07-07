package com.perfulandia.perfulandia.controller;

import com.perfulandia.perfulandia.model.Trabajador;
import com.perfulandia.perfulandia.service.TrabajadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trabajador")
@Tag(name="Trabajador", description="Operaciones relacionadas con los trabajadores")

public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    // Crear un nuevo trabajador
    @PostMapping
    @Operation(summary = "Agregar Trabajador", description = "Se agrega un nuevo trabajador")
    public ResponseEntity<Trabajador> crearTrabajador(@RequestBody Trabajador trabajador) {
        Trabajador nuevoTrabajador = trabajadorService.crearTrabajador(trabajador);
        return new ResponseEntity<>(nuevoTrabajador, HttpStatus.CREATED);
    }

    // Obtener todos los trabajadores
    @GetMapping
    @Operation(summary = "Obtener todos los trabajadores", description = "Obtiene una lista de todos los trabajadores")
    public ResponseEntity<List<Trabajador>> obtenerTodosLosTrabajadores() {
        List<Trabajador> trabajadores = trabajadorService.obtenerTodosLosTrabajadores();
        return new ResponseEntity<>(trabajadores, HttpStatus.OK);
    }

    // Obtener un trabajador por ID
    @GetMapping("/id/{id}")
    @Operation(summary = "Obtener un trabajador con el id indicado", description = "Obtiene el trabajador con el ID ingresado")

    public Trabajador obtenerTrabajadorPorId(@PathVariable int id) {
        Trabajador trabajador = trabajadorService.obtenerTrabajadorPorId(id);
        return trabajador;
    }

    //Obtener trabajadores por Nombre
    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Obtener todos los trabajadores con el nombre indicada", description = "Obtiene una lista de todos los trabajadores con el respectivo nombre ingresado")

    public ResponseEntity<List<Trabajador>> obtenerTrabajadorPorNombre(@PathVariable String nombre) {
        List<Trabajador> trabajadores = trabajadorService.obtenerTrabajadorPorNombre(nombre);
        return new ResponseEntity<>(trabajadores, HttpStatus.OK);
    }


    // Actualizar un trabajador existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un trabajador existente", description = "Actualiza un trabajador existente ingresando el ID")
    public ResponseEntity<Trabajador> actualizarTrabajador(@PathVariable int id, @RequestBody Trabajador trabajador) {
        Trabajador trabajadorActualizado = trabajadorService.actualizarTrabajador(id, trabajador);
        return (trabajadorActualizado != null) ?
                new ResponseEntity<>(trabajadorActualizado, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Eliminar un trabajador por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un trabajador existente", description = "Elimina un trabajador existente ingresando el ID correspondiente")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable int id) {
        boolean eliminado = trabajadorService.eliminarTrabajador(id);
        return eliminado ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}