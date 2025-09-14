package co.analisys.clases.controller.impl;

import co.analisys.clases.controller.interfaces.ClaseController;
import co.analisys.clases.dto.ClaseOutDTO;
import co.analisys.clases.model.Clase;
import co.analisys.clases.service.interfaces.ClaseService;
import co.analisys.clases.service.impl.EntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ClaseControllerImpl implements ClaseController {

    private final ClaseService claseService;
    private final EntrenadorService entrenadorService;

    @Override
    public Clase crearClase(Clase clase) {
        return claseService.crearClase(clase);
    }

    @Override
    public List<ClaseOutDTO> listarClases() {
        return claseService.listarClases();
    }

    @Override
    public void inscribir(Long claseId, String usuarioId) {
        claseService.inscribirAlumno(claseId, usuarioId);
    }

    @Override
    public Clase actualizarHorario(Long claseId, String nuevoHorarioISO) {
        LocalDateTime nuevo = LocalDateTime.parse(nuevoHorarioISO);
        return claseService.actualizarHorario(claseId, nuevo);
    }

}