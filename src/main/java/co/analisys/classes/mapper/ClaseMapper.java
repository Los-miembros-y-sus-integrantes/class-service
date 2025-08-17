package co.analisys.classes.mapper;

import co.analisys.classes.dto.ClaseOutDTO;
import co.analisys.classes.model.Clase;

public class ClaseMapper {


     public static ClaseOutDTO toDto(Clase clase) {
         if (clase == null) {
             return null;
         }
         ClaseOutDTO dto = new ClaseOutDTO();
         dto.setId(clase.getId());
         dto.setNombre(clase.getNombre());
         dto.setHorario(clase.getHorario());
         dto.setCapacidadMaxima(clase.getCapacidadMaxima());
         //TODO: obtener entrenador del servicio de entrenadores
         //dto.setEntrenador(EntrenadorMapper.toDto(clase.getEntrenador()));
         return dto;
     }
}
