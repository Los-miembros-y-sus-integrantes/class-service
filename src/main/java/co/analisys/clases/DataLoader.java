package co.analisys.clases;

import co.analisys.clases.model.*;
import co.analisys.clases.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClaseRepository claseRepository;

    @Override
    public void run(String... args) throws Exception {
        
        // Cargar clases de ejemplo con IDs de entrenadores mockeados
        Clase clase1 = new Clase();
        clase1.setNombre("Yoga Matutino");
        clase1.setHorario(LocalDateTime.now().plusDays(1).withHour(8).withMinute(0));
        clase1.setCapacidadMaxima(20);
        clase1.setEntrenadorId(1L); // ID mockeado del entrenador
        claseRepository.save(clase1);

        Clase clase2 = new Clase();
        clase2.setNombre("Spinning Vespertino");
        clase2.setHorario(LocalDateTime.now().plusDays(1).withHour(18).withMinute(0));
        clase2.setCapacidadMaxima(15);
        clase2.setEntrenadorId(2L); // ID mockeado del entrenador
        claseRepository.save(clase2);

        System.out.println("Datos de clases de ejemplo cargados exitosamente.");
    }
}