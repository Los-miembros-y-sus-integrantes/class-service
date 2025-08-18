package co.analisys.clases.service.impl;

import co.analisys.clases.dto.EntrenadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EntrenadorService {

    @Autowired
    private RestTemplate restTemplate;

    private final String ENTRENADOR_SERVICE_URL = "http://localhost:8081/entrenadores";

    public boolean existeEntrenador(Long entrenadorId) {
        try {
            return entrenadorId != null && (entrenadorId == 1L || entrenadorId == 2L);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene los datos de un entrenador desde el microservicio
     * Por ahora es un mock
     */
    public EntrenadorDTO obtenerEntrenador(Long entrenadorId) {
        try {
            if (entrenadorId == 1L) {
                return new EntrenadorDTO(1L, "Carlos Rodríguez", "Yoga");
            } else if (entrenadorId == 2L) {
                return new EntrenadorDTO(2L, "Ana Martínez", "Spinning");
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
