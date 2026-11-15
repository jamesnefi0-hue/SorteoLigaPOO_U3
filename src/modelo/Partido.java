package modelo;

import java.io.Serializable;

/**
 * Representa un enfrentamiento entre un equipo local y un equipo visitante.
 */
public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Equipo local;
    private final Equipo visitante;
    private Equipo ganador;

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    public Equipo getLocal() {
        return local;
    }

    public Equipo getVisitante() {
        return visitante;
    }

    public Equipo getGanador() {
        return ganador;
    }

    void setGanador(Equipo ganador) {
        this.ganador = ganador;
    }

    @Override
    public String toString() {
        String texto = local + " vs " + visitante;
        if (ganador != null) {
            texto += "  ->  avanza: " + ganador;
        }
        return texto;
    }
}
