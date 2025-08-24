package co.analisys.clases.service.impl;

import co.analisys.clases.dto.ClaseOutDTO;
import co.analisys.clases.dto.EntrenadorDTO;
import co.analisys.clases.model.Clase;
import co.analisys.clases.repository.ClaseRepository;
import co.analisys.clases.service.interfaces.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaseServiceImpl implements ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private EntrenadorService entrenadorService;

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
