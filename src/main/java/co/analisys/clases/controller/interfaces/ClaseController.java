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

    @PostMapping("/{id}/inscripciones")
    @Operation(summary = "Inscribir un alumno a la clase (publica evento)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "202", description = "Inscripción aceptada (evento publicado)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    void inscribir(@PathVariable("id") Long claseId, @RequestParam(required = false) String usuarioId);

    @PatchMapping("/{id}/horario")
    @Operation(summary = "Actualizar el horario de una clase (publica evento)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Horario actualizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    Clase actualizarHorario(@PathVariable("id") Long claseId, @RequestParam("nuevo") String nuevoHorarioISO);

    @PostMapping("/{id}/ocupacion")
    @Operation(summary = "Actualizar la ocupación de una clase (publica evento)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Ocupación actualizada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Clase no encontrada")
    })
    void actualizarOcupacion(@PathVariable("id") Long claseId, @RequestParam("ocupacion") int ocupacion);

}