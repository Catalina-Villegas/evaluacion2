package com.perfulandia.perfulandia.service;


import com.perfulandia.perfulandia.model.Trabajador;
import com.perfulandia.perfulandia.repository.TrabajadorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    // Obtener todos los trabajadores
    public List<Trabajador> obtenerTodosLosTrabajadores() {
        return trabajadorRepository.findAll();
    }
    // Obtener un trabajador por ID
    public Trabajador obtenerTrabajadorPorId(int id) {
        return trabajadorRepository.findById(id);
    }

    // Obtener trabajadores por nombre
    public List<Trabajador> obtenerTrabajadorPorNombre(String nombre) {
        return trabajadorRepository.findByNombre(nombre);
    }

    // Obtener trabajadores por apellido paterno
    public List<Trabajador> obtenerTrabajadorPorApellidoParterno(String apellidop) {
        return trabajadorRepository.findByApellidop(apellidop);
    }

    // Crear un nuevo trabajador
    public Trabajador crearTrabajador(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    // Actualizar un trabajador existente
    public Trabajador actualizarTrabajador(int id, Trabajador trabajadorActualizado) {
        if (trabajadorRepository.existsById(id)) {
            trabajadorActualizado.setId(id);
            return trabajadorRepository.save(trabajadorActualizado);
        }
        return null;
    }

    // Eliminar un trabajador por ID
    public boolean eliminarTrabajador(int id) {
        if (trabajadorRepository.existsById(id)) {
            trabajadorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
