package co.analisys.clases.controller.impl;

import co.analisys.clases.controller.interfaces.ClaseController;
import co.analisys.clases.dto.ClaseOutDTO;
import co.analisys.clases.model.Clase;
import co.analisys.clases.service.interfaces.ClaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClaseControllerImpl implements ClaseController {

    private final ClaseService claseService;

    @Override
    public Clase crearClase(Clase clase) {
        return claseService.crearClase(clase);
    }

    @Override
    public List<ClaseOutDTO> listarClases() {
        return claseService.listarClases();
    }
}