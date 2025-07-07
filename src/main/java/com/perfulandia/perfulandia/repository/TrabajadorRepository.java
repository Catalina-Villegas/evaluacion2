package com.perfulandia.perfulandia.repository;

import com.perfulandia.perfulandia.model.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Long> {

    Boolean existsById(int id);;
    Trabajador findById(int id);
    List<Trabajador> findByNombre(String nombre);
    List<Trabajador> findByApellidop(String apellidop);
    void deleteById(int id);

}
