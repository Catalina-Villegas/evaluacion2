package com.perfulandia.perfulandia;

import com.perfulandia.perfulandia.model.Perfume;
import com.perfulandia.perfulandia.model.Trabajador;
import com.perfulandia.perfulandia.repository.PerfumeRepository;
import com.perfulandia.perfulandia.repository.TrabajadorRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Profile("dev")
@Component

public class DataLoader implements CommandLineRunner {
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private PerfumeRepository perfumeRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es"));
        Random random = new Random();

        // Generar trabajadores
        for (int i = 0; i < 10; i++) {
            Trabajador trabajador = new Trabajador();
            trabajador.setNombre(faker.name().firstName());
            trabajador.setApellidop(faker.name().lastName());
            trabajador.setApellidom(faker.name().lastName());
            trabajadorRepository.save(trabajador);
        }

        // Generar perfumes
        for (int i = 0; i < 20; i++) {
            Perfume perfume = new Perfume();
            perfume.setNombre(faker.commerce().productName());
            perfume.setMarca(faker.company().name());
            perfume.setGenero(faker.options().option("M", "F", "U")); // M: Masculino, F: Femenino, U: Unisex
            perfume.setDescription(faker.lorem().sentence());
            perfume.setAnio(faker.number().numberBetween(1990, 2025));
            perfume.setCantidad(faker.number().numberBetween(1, 100));
            perfume.setPrecio(faker.number().numberBetween(10000, 100000));
            perfumeRepository.save(perfume);
        }
    }
}
