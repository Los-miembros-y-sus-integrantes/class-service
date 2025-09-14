package co.analisys.clases.messaging;

import java.io.Serializable;

/**
 * Evento publicado cuando un usuario se inscribe a una clase.
 * No persistimos la inscripción; solo comunicamos el hecho.
 */
public class ClaseInscripcionCreadaEvent implements Serializable {
    private Long claseId;
    private String nombreClase;
    private String horario;
    private String usuarioId; // podría ser null si aún no manejamos usuarios reales

    public ClaseInscripcionCreadaEvent() {
    }

    public ClaseInscripcionCreadaEvent(Long claseId, String nombreClase, String horario, String usuarioId) {
        this.claseId = claseId;
        this.nombreClase = nombreClase;
        this.horario = horario;
        this.usuarioId = usuarioId;
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
}
