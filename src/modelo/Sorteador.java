package modelo;

import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Contiene la lógica del sorteo. El emparejamiento de cada etapa se hace
 * con un algoritmo RECURSIVO.
 */
public class Sorteador implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Random random;

    public Sorteador() {
        this.random = new Random();
    }

    /**
     * Verifica que la cantidad de equipos sea potencia de 2 (16, 8, 4, 2).
     */
    public void validarNumeroEquipos(int cantidad) throws NumeroEquiposInvalidoException {
        // Un número es potencia de 2 si tiene un solo bit encendido: n & (n - 1) == 0
        if (cantidad < 2 || (cantidad & (cantidad - 1)) != 0) {
            throw new NumeroEquiposInvalidoException(
                    "El número de equipos (" + cantidad + ") no es válido para eliminación directa. "
                    + "Debe ser una potencia de 2 (16, 8, 4 o 2).");
        }
    }

    /**
     * Punto de entrada del sorteo de una etapa.
     */
    public List<Partido> sortearEtapa(List<Equipo> equipos)
            throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        validarNumeroEquipos(equipos.size());

        // No se aceptan equipos repetidos dentro de una misma etapa
        Set<Equipo> sinRepetidos = new HashSet<>(equipos);
        if (sinRepetidos.size() != equipos.size()) {
            throw new SorteoInvalidoException("Hay equipos repetidos en la etapa.");
        }

        List<Partido> partidos = new ArrayList<>();
        emparejarRecursivo(new ArrayList<>(equipos), partidos, new HashSet<>());
        return partidos;
    }

    /**
     * MÉTODO RECURSIVO.
     * Caso base: no quedan equipos por emparejar.
     * Caso recursivo: se elige al azar un equipo local y un rival distinto,
     * se crea el partido, se retiran ambos de la lista y se llama de nuevo
     * al método con los equipos restantes.
     *
     * @param disponibles equipos que aún no tienen rival
     * @param partidos    partidos generados hasta el momento
     * @param yaAsignados equipos que ya juegan en esta etapa (control de repetición)
     */
    private void emparejarRecursivo(List<Equipo> disponibles, List<Partido> partidos,
                                    Set<Equipo> yaAsignados) throws SorteoInvalidoException {
        // Caso base
        if (disponibles.isEmpty()) {
            return;
        }
        // Condición imposible: un equipo se queda sin rival
        if (disponibles.size() == 1) {
            throw new SorteoInvalidoException(
                    "El equipo " + disponibles.get(0) + " se quedó sin oponente.");
        }

        Equipo local = disponibles.remove(random.nextInt(disponibles.size()));
        Equipo visitante = disponibles.remove(random.nextInt(disponibles.size()));

        // Un equipo no puede ser competidor y oponente a la vez
        if (local.equals(visitante)) {
            throw new SorteoInvalidoException(
                    "El equipo " + local + " no puede enfrentarse a sí mismo.");
        }
        // Ningún equipo puede repetir partido en la misma etapa
        if (!yaAsignados.add(local) || !yaAsignados.add(visitante)) {
            throw new SorteoInvalidoException(
                    "Un equipo ya tiene partido asignado en esta etapa.");
        }

        partidos.add(new Partido(local, visitante));

        // Llamada recursiva con los equipos que quedan
        emparejarRecursivo(disponibles, partidos, yaAsignados);
    }

    /**
     * Define al azar el ganador de cada partido (simulación) y devuelve
     * la lista de equipos que avanzan a la siguiente etapa.
     */
    public List<Equipo> definirGanadores(List<Partido> partidos) {
        List<Equipo> clasificados = new ArrayList<>();
        for (Partido p : partidos) {
            Equipo ganador = random.nextBoolean() ? p.getLocal() : p.getVisitante();
            p.setGanador(ganador);
            clasificados.add(ganador);
        }
        return clasificados;
    }
}
