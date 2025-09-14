package co.analisys.clases.messaging;

import java.io.Serializable;

/**
 * Evento publicado cuando el horario de una clase cambia.
 */
public class ClaseHorarioActualizadoEvent implements Serializable {
    private Long claseId;
    private String nombreClase;
    private String horarioAnterior;
    private String horarioNuevo;

    public ClaseHorarioActualizadoEvent() {}

    public ClaseHorarioActualizadoEvent(Long claseId, String nombreClase, String horarioAnterior, String horarioNuevo) {
        this.claseId = claseId;
        this.nombreClase = nombreClase;
        this.horarioAnterior = horarioAnterior;
        this.horarioNuevo = horarioNuevo;
    }

    public Long getClaseId() {
        return claseId;
    }

    public void setClaseId(Long claseId) {
        this.claseId = claseId;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getHorarioAnterior() {
        return horarioAnterior;
    }

    public void setHorarioAnterior(String horarioAnterior) {
        this.horarioAnterior = horarioAnterior;
    }

    public String getHorarioNuevo() {
        return horarioNuevo;
    }

    public void setHorarioNuevo(String horarioNuevo) {
        this.horarioNuevo = horarioNuevo;
    }
}
