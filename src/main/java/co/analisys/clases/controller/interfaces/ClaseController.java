package co.analisys.clases.controller.interfaces;

import co.analisys.clases.dto.ClaseOutDTO;
import co.analisys.clases.model.Clase;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RequestMapping("/clases")
@Tag(name = "Clase", description = "Operaciones relacionadas con las clases")
public interface ClaseController {

    @PostMapping()
    @Operation(summary = "Crear una nueva clase")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Clase creada exitosamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public Clase crearClase(@RequestBody Clase clase);
    
    @GetMapping()
    @Operation(summary = "Listar todas las clases")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Clases listadas exitosamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "No se encontraron clases")
    })
    public List<ClaseOutDTO> listarClases();

}