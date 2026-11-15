package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Crea la lista inicial de 16 equipos de la liga.
 */
public class LigaFactory {

    private static final String[] NOMBRES = {
        "Barcelona SC", "Emelec", "Liga de Quito", "Independiente del Valle",
        "Aucas", "El Nacional", "Deportivo Cuenca", "Universidad Católica",
        "Delfín SC", "Orense SC", "Macará", "Técnico Universitario",
        "Mushuc Runa", "Libertad FC", "Imbabura SC", "Guayaquil City"
    };

    private LigaFactory() {
    }

    public static List<Equipo> crearEquiposIniciales() {
        List<Equipo> equipos = new ArrayList<>();
        for (int i = 0; i < NOMBRES.length; i++) {
            equipos.add(new Equipo(i + 1, NOMBRES[i]));
        }
        return equipos;
    }
}
