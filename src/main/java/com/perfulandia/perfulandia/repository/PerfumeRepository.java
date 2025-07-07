package com.perfulandia.perfulandia.repository;

import com.perfulandia.perfulandia.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Integer> {

    Boolean existsById(int id);
    Perfume findById(int id);
    List<Perfume> findByMarca(String marca);
    List<Perfume> findByGenero(String genero);
    void deleteById(int id);

} // FINAL REPOSITORY