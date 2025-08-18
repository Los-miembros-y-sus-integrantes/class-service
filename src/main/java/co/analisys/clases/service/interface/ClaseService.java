package co.analisys.clases.service.interfaces;

import co.analisys.clases.dto.ClaseOutDTO;
import co.analisys.clases.model.Clase;

import java.util.List;

public interface ClaseService {

    public Clase crearClase(Clase clase);
    public List<ClaseOutDTO> listarClases();
}
