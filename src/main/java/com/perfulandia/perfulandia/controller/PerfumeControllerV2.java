package com.perfulandia.perfulandia.controller;


import com.perfulandia.perfulandia.assemblers.PerfumeModelAssembler;
import com.perfulandia.perfulandia.model.Perfume;
import com.perfulandia.perfulandia.service.PerfumeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;

import org.springframework.http.MediaType;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/perfumes")
@Tag(name="Perfumes", description="Operaciones relacionadas con los perfumes")
public class PerfumeControllerV2 {

    @Autowired
    private PerfumeService perfumeService;

    @Autowired
    private PerfumeModelAssembler assembler;


    // Crear un nuevo perfume
    @PostMapping
    public ResponseEntity<Perfume> crearPerfume(@RequestBody Perfume perfume) {
        Perfume nuevoPerfume = perfumeService.crearPerfume(perfume);
        return new ResponseEntity<>(nuevoPerfume, HttpStatus.CREATED);
    }

    //Obtener todos los perfumes con assembler y HATEOAS
    @GetMapping(value="/hateoas",produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Perfume>> getAllPerfumesHATEOAS() {
        List<Perfume> lista = perfumeService.obtenerTodosLosPerfumes();

        List<EntityModel<Perfume>> perfumes = lista.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(perfumes,
                linkTo(methodOn(PerfumeControllerV2.class).getAllPerfumesHATEOAS()).withSelfRel());
    }

    //Obtener todos los perfumes solo con HATEOAS
    @GetMapping(value="/hateoasV2",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Perfume>>> getAllPerfumesHATEOASv2() {
        List<Perfume> lista = perfumeService.obtenerTodosLosPerfumes();
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<EntityModel<Perfume>> perfumes = lista.stream()
                .map(perfume -> EntityModel.of(perfume,
                        linkTo(methodOn(PerfumeControllerV2.class)
                                .obtenerPerfumePorId(perfume.getId()))
                                .withSelfRel()))
                .toList();

        CollectionModel<EntityModel<Perfume>> collectionModel = CollectionModel.of(
                perfumes,
                linkTo(methodOn(PerfumeControllerV2.class).getAllPerfumesHATEOASv2()).withSelfRel()
        );
        return ResponseEntity.ok(collectionModel);
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
    public Perfume obtenerPerfumePorId(@PathVariable int id) {
        Perfume perfume = perfumeService.obtenerPerfumePorId(id);
        return perfume;
    }

    //Obtener un perfume por id con HATEOAS
    @GetMapping(value="/hateoas/{codigo}",produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar una carrera por codigo + HATEOAS", description = "Busca una carrera existente")
    public ResponseEntity<EntityModel<Perfume>> getByIdPerfumeHATEOAS(@PathVariable int codigo) {
        try {
            Perfume perfume = perfumeService.obtenerPerfumePorId(codigo);
            if (perfume == null) {return ResponseEntity.notFound().build();}

            EntityModel<Perfume> perfumeEntityModel= EntityModel.of(perfume,
                    linkTo(methodOn(PerfumeControllerV2.class)
                            .getByIdPerfumeHATEOAS(perfume.getId())).withSelfRel());

            return ResponseEntity.ok(perfumeEntityModel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
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
