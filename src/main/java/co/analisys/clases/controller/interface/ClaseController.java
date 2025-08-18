package co.analisys.clases.controller.interfaces;

import co.analisys.clases.dto.ClaseOutDTO;
import co.analisys.clases.model.Clase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clases")
public interface ClaseController {

    @PostMapping()
    public Clase crearClase(@RequestBody Clase clase);
    
    @GetMapping()
    public List<ClaseOutDTO> listarClases();
}