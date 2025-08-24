package co.analisys.clases;

import co.analisys.clases.model.*;
import co.analisys.clases.repository.ClaseRepository;
import co.analisys.clases.dto.EntrenadorDTO;
import co.analisys.clases.service.impl.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClaseRepository claseRepository;
    
    @Autowired
    private EntrenadorService entrenadorService;

    @Override
    public void run(String... args) throws Exception {
        
        try {
            // Obtener todos los entrenadores del microservicio
            List<EntrenadorDTO> entrenadores = entrenadorService.obtenerTodosLosEntrenadores();
            
            if (entrenadores == null || entrenadores.isEmpty()) {
                System.out.println("No se pudieron obtener entrenadores del microservicio. Usando IDs por defecto.");
                cargarClasesConEntrenadoresFijos();
                return;
            }
            
            System.out.println("Entrenadores obtenidos del microservicio: " + entrenadores.size());
            
            // Cargar clases de ejemplo asignando entrenadores en orden
            Clase clase1 = new Clase();
            clase1.setNombre("Yoga Matutino");
            clase1.setHorario(LocalDateTime.now().plusDays(1).withHour(8).withMinute(0));
            clase1.setCapacidadMaxima(20);
            clase1.setEntrenadorId(entrenadores.get(0).getId()); // Primer entrenador
            claseRepository.save(clase1);

            Clase clase2 = new Clase();
            clase2.setNombre("Spinning Vespertino");
            clase2.setHorario(LocalDateTime.now().plusDays(1).withHour(18).withMinute(0));
            clase2.setCapacidadMaxima(15);
            if(entrenadores.size() > 1) {
                clase2.setEntrenadorId(entrenadores.get(1).getId()); // Segundo entrenador si existe
            } else {
                clase2.setEntrenadorId(entrenadores.get(0).getId()); // Reutilizar el primero
            }
            claseRepository.save(clase2);

            
        } catch (Exception e) {
            System.err.println("Error al obtener entrenadores del microservicio: " + e.getMessage());
            System.out.println("Cargando clases con entrenadores por defecto...");
            System.out.println("--------------------------------------------------------------------------------");
        }
    }
}