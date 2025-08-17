package co.analisys.classes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EntrenadorDTO {
    private Long id;
    private String nombre;
    private String especialidad;
}
