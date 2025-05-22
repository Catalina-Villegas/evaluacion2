package com.perfulandia.perfulandia.service;

import com.perfulandia.perfulandia.model.Perfume;
import com.perfulandia.perfulandia.repository.PerfumeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PerfumeService {

    @Autowired
    private PerfumeRepository perfumeRepository;

    // Obtener todos los perfumes
    public List<Perfume> obtenerTodosLosPerfumes() {
        return perfumeRepository.findAll();
    }
    // Obtener un perfume por ID
    public Perfume obtenerPerfumePorId(int id) {
        return perfumeRepository.findById(id);
    }

    // Obtener un perfume por Marca
    public List<Perfume> obtenerPerfumePorMarca(String marca) {
        return perfumeRepository.findByMarca(marca);
    }

    // Obtener un perfume por Genero
    public List<Perfume> obtenerPerfumePorGenero(String genero) {
        return perfumeRepository.findByGenero(genero);
    }

    // Crear un nuevo perfume
    public Perfume crearPerfume(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }

    // Actualizar un perfume existente
    public Perfume actualizarPerfume(int id, Perfume perfumeActualizado) {
        if (perfumeRepository.existsById(id)) {
            perfumeActualizado.setId(id);
            return perfumeRepository.save(perfumeActualizado);
        }
        return null;
    }

    // Eliminar un perfume por ID
    public boolean eliminarPerfume(int id) {
        if (perfumeRepository.existsById(id)) {
            perfumeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}// FINAL CLASE SERVICE
