package co.analisys.clases.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OcupacionClaseDTO {
    
    Long idClase;
    Integer ocupacion;
    LocalDateTime hora;
}
