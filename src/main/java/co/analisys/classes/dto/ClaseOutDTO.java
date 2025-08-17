package co.analisys.classes.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaseOutDTO {

    private Long id;
    private String nombre;
    private LocalDateTime horario;
    private int capacidadMaxima;

    private EntrenadorDTO entrenador;
}
