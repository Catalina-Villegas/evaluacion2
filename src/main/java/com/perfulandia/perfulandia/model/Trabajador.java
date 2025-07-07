package com.perfulandia.perfulandia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="trabajador")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Trabajador {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false)
        private String nombre;

        @Column(nullable = false)
        private String apellidop;

        @Column(nullable = false)
        private String apellidom;

}
