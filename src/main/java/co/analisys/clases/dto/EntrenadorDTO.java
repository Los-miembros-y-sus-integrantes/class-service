package co.analisys.clases.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorDTO {
    private Long id;
    private String nombre;
    private String especialidad;
}
