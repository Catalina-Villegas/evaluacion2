package com.perfulandia.perfulandia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="perfume")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Perfume {
    @Id
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false,length = 1)
    private String genero;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 4)
    private int anio;

    @Column(nullable = true)
    private int cantidad;

    @Column(nullable = false)
    private int precio;

} //FINAL CLASE Perfume

