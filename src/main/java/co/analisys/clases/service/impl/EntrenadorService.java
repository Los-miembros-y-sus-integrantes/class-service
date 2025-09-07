package co.analisys.clases.service.impl;

import co.analisys.clases.client.EntrenadorClient;
import co.analisys.clases.dto.EntrenadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorClient entrenadorClient;

    public boolean existeEntrenador(Long entrenadorId) {
        try {
            EntrenadorDTO entrenador = entrenadorClient.obtenerPorId(entrenadorId);
            return entrenador != null;
        } catch (Exception e) {
            System.err.println("Error al verificar existencia del entrenador: " + e.getMessage());
            return false;
        }
    }

    public EntrenadorDTO obtenerEntrenador(Long entrenadorId) {
        try {
            return entrenadorClient.obtenerPorId(entrenadorId);
        } catch (Exception e) {
            System.err.println("Error al obtener entrenador con ID " + entrenadorId + ": " + e.getMessage());
            return null;
        }
    }

    public List<EntrenadorDTO> obtenerTodosLosEntrenadores() {
        try {
            return entrenadorClient.obtenerTodos();
        } catch (Exception e) {
            System.err.println("Error al obtener lista de entrenadores: " + e.getMessage());
            return null;
        }
    }
}
