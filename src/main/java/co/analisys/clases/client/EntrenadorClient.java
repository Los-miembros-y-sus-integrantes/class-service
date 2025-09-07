package co.analisys.clases.client;

import co.analisys.clases.dto.EntrenadorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "entrenador-service", url = "http://localhost:8082")
public interface EntrenadorClient {

    @GetMapping("/entrenadores")
    List<EntrenadorDTO> obtenerTodos();

    @GetMapping("/entrenadores/{id}")
    EntrenadorDTO obtenerPorId(@PathVariable("id") Long id);
}
