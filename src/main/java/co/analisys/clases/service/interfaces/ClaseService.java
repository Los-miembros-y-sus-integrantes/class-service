package co.analisys.clases.service.interfaces;

import co.analisys.clases.dto.ClaseOutDTO;
import co.analisys.clases.model.Clase;

import java.util.List;

public interface ClaseService {

    public Clase crearClase(Clase clase);
    public List<ClaseOutDTO> listarClases();
    // Nueva operación: inscribir (solo publica evento, no persiste relación usuario-clase)
    void inscribirAlumno(Long claseId, String usuarioId);
    // Actualizar horario de la clase y publicar evento
    Clase actualizarHorario(Long claseId, java.time.LocalDateTime nuevoHorario);
    
    public void actualizarOcupacion(Long claseId, Integer nuevaOcupacion);
}
