package co.analisys.clases.service.impl;

import co.analisys.clases.dto.ClaseOutDTO;
import co.analisys.clases.dto.EntrenadorDTO;
import co.analisys.clases.model.Clase;
import co.analisys.clases.repository.ClaseRepository;
import co.analisys.clases.service.interfaces.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import static co.analisys.clases.messaging.RabbitConfig.*;
import co.analisys.clases.messaging.ClaseInscripcionCreadaEvent;
import co.analisys.clases.messaging.ClaseHorarioActualizadoEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaseServiceImpl implements ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private EntrenadorService entrenadorService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Clase crearClase(Clase clase) {
        // Verificar que el entrenador existe antes de crear la clase
        if (clase.getEntrenadorId() != null && !entrenadorService.existeEntrenador(clase.getEntrenadorId())) {
            throw new RuntimeException("El entrenador con ID " + clase.getEntrenadorId() + " no existe");
        }
        return claseRepository.save(clase);
    }

    @Override
    public List<ClaseOutDTO> listarClases() {
        List<Clase> clases = claseRepository.findAll();
        return clases.stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public void inscribirAlumno(Long claseId, String usuarioId) {
    Clase clase = claseRepository.findById(claseId)
        .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
    ClaseInscripcionCreadaEvent event = new ClaseInscripcionCreadaEvent(
        clase.getId(),
        clase.getNombre(),
        clase.getHorario() != null ? clase.getHorario().toString() : null,
        usuarioId
    );
    rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_CLASE_INSCRIPCION_CREADA, event);
    }

    @Override
    public Clase actualizarHorario(Long claseId, java.time.LocalDateTime nuevoHorario) {
    Clase clase = claseRepository.findById(claseId)
        .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
    String anterior = clase.getHorario() != null ? clase.getHorario().toString() : null;
    clase.setHorario(nuevoHorario);
    Clase actualizado = claseRepository.save(clase);
    ClaseHorarioActualizadoEvent event = new ClaseHorarioActualizadoEvent(
        actualizado.getId(),
        actualizado.getNombre(),
        anterior,
        actualizado.getHorario() != null ? actualizado.getHorario().toString() : null
    );
    rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY_CLASE_HORARIO_ACTUALIZADO, event);
    return actualizado;
    }

    private ClaseOutDTO convertirADTO(Clase clase) {
        EntrenadorDTO entrenador = null;
        if (clase.getEntrenadorId() != null) {
            entrenador = entrenadorService.obtenerEntrenador(clase.getEntrenadorId());
        }

        return ClaseOutDTO.builder()
                .id(clase.getId())
                .nombre(clase.getNombre())
                .horario(clase.getHorario())
                .capacidadMaxima(clase.getCapacidadMaxima())
                .entrenador(entrenador)
                .build();
    }
}
