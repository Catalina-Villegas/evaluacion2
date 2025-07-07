package com.perfulandia.perfulandia;

import com.perfulandia.perfulandia.model.Perfume;
import com.perfulandia.perfulandia.repository.PerfumeRepository;
import com.perfulandia.perfulandia.service.PerfumeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class PerfumeServiceTest {

    @InjectMocks
    private PerfumeService perfumeService;

    @Mock
    private PerfumeRepository perfumeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCrearPerfume() {
        Perfume perfume = new Perfume(1, "Invictus", "Paco Rabanne", "M", "Fresco", 2020, 10, 59990);
        when(perfumeRepository.save(perfume)).thenReturn(perfume);

        Perfume resultado = perfumeService.crearPerfume(perfume);
        assertEquals("Invictus", resultado.getNombre());
        verify(perfumeRepository, times(1)).save(perfume);
    }

    @Test
    void testObtenerPerfumePorId() {
        Perfume perfume = new Perfume(1, "Invictus", "Paco Rabanne", "M", "Fresco", 2020, 10, 59990);
        when(perfumeRepository.findById(1)).thenReturn(perfume);

        Perfume resultado = perfumeService.obtenerPerfumePorId(1);
        assertEquals("Invictus", resultado.getNombre());
        verify(perfumeRepository).findById(1);
    }


    @Test
    public void testEliminarPerfume_Exitoso() {
        int id = 1;
        when(perfumeRepository.existsById(id)).thenReturn(true);
        doNothing().when(perfumeRepository).deleteById(id);

        boolean resultado = perfumeService.eliminarPerfume(id);
        assertTrue(resultado);
        verify(perfumeRepository, times(1)).deleteById(id);
    }



}
