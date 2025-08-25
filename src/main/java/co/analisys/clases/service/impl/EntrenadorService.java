package co.analisys.clases.service.impl;

import co.analisys.clases.dto.EntrenadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class EntrenadorService {

    @Autowired
    private RestTemplate restTemplate;

    private final String ENTRENADOR_SERVICE_URL = "http://localhost:8082/entrenadores";
    
    // Cache para evitar múltiples llamadas al microservicio
    private List<EntrenadorDTO> entrenadoresCache = null;

    public boolean existeEntrenador(Long entrenadorId) {
        try {
            List<EntrenadorDTO> entrenadores = obtenerTodosLosEntrenadores();
            if (entrenadores == null) return false;
            
            return entrenadores.stream()
                    .anyMatch(entrenador -> entrenador.getId().equals(entrenadorId));
        } catch (Exception e) {
            System.err.println("Error al verificar existencia del entrenador: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene los datos de un entrenador específico desde la lista de todos los entrenadores
     */
    public EntrenadorDTO obtenerEntrenador(Long entrenadorId) {
        try {
            List<EntrenadorDTO> entrenadores = obtenerTodosLosEntrenadores();
            if (entrenadores == null) return null;
            
            return entrenadores.stream()
                    .filter(entrenador -> entrenador.getId().equals(entrenadorId))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            System.err.println("Error al obtener entrenador con ID " + entrenadorId + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene todos los entrenadores desde el microservicio de entrenadores
     */
    public List<EntrenadorDTO> obtenerTodosLosEntrenadores() {
        try {
            // Si ya tenemos los datos en cache, los devolvemos
            if (entrenadoresCache != null) {
                return entrenadoresCache;
            }
            
            ResponseEntity<List<EntrenadorDTO>> response = restTemplate.exchange(
                ENTRENADOR_SERVICE_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EntrenadorDTO>>() {}
            );
            
            // Guardar en cache para futuras consultas
            entrenadoresCache = response.getBody();
            return entrenadoresCache;
        } catch (Exception e) {
            System.err.println("Error al obtener lista de entrenadores: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Limpia el cache de entrenadores (útil si necesitas refrescar los datos)
     */
    public void limpiarCache() {
        entrenadoresCache = null;
    }
}
