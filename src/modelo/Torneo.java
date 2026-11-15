package modelo;

import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase principal del Modelo: guarda el estado completo del torneo.
 * Implementa Serializable para poder guardarse en torneo.ser.
 */
public class Torneo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String nombre;
    private final List<Equipo> equipos;
    private final List<Etapa> etapas;
    private final Sorteador sorteador;
    private Equipo campeon;
    private String fechaSorteo;

    public Torneo(String nombre, List<Equipo> equipos) throws NumeroEquiposInvalidoException {
        this.nombre = nombre;
        this.equipos = new ArrayList<>(equipos);
        this.etapas = new ArrayList<>();
        this.sorteador = new Sorteador();
        // Se valida desde la creación que el torneo arranque con un número correcto
        sorteador.validarNumeroEquipos(equipos.size());
    }

    /**
     * Realiza el sorteo de todas las etapas, desde octavos hasta la final.
     */
    public void realizarSorteo() throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        etapas.clear();
        campeon = null;
        sortearEtapasRecursivo(new ArrayList<>(equipos));
        fechaSorteo = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Recorre las etapas de forma recursiva: sortea la etapa actual,
     * define los clasificados y vuelve a llamarse con ellos hasta que
     * queda un solo equipo (el campeón).
     */
    private void sortearEtapasRecursivo(List<Equipo> participantes)
            throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        if (participantes.size() == 1) {      // caso base: hay campeón
            campeon = participantes.get(0);
            return;
        }
        List<Partido> partidos = sorteador.sortearEtapa(participantes);
        List<Equipo> clasificados = sorteador.definirGanadores(partidos);
        etapas.add(new Etapa(nombreEtapa(participantes.size()), partidos));
        sortearEtapasRecursivo(clasificados);
    }

    private String nombreEtapa(int cantidadEquipos) {
        switch (cantidadEquipos) {
            case 16: return "OCTAVOS DE FINAL";
            case 8:  return "CUARTOS DE FINAL";
            case 4:  return "SEMIFINALES";
            case 2:  return "FINAL";
            default: return "RONDA DE " + cantidadEquipos;
        }
    }

    public boolean tieneSorteo() {
        return !etapas.isEmpty();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Equipo> getEquipos() {
        return Collections.unmodifiableList(equipos);
    }

    public List<Etapa> getEtapas() {
        return Collections.unmodifiableList(etapas);
    }

    public Equipo getCampeon() {
        return campeon;
    }

    public String getFechaSorteo() {
        return fechaSorteo;
    }
}
