package vista;

import modelo.Etapa;
import modelo.Partido;
import modelo.Torneo;
import modelo.Equipo;

import java.util.Scanner;

/**
 * Vista: solo se encarga de la entrada y salida por consola.
 * No contiene lógica de negocio.
 */
public class VistaConsola {

    private final Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarBienvenida() {
        System.out.println("==============================================");
        System.out.println("     SORTEO DE LA LIGA PROFESIONAL (16)");
        System.out.println("==============================================");
    }

    public int pedirOpcionMenu() {
        System.out.println();
        System.out.println("------------------ MENÚ ----------------------");
        System.out.println(" 1. Realizar nuevo sorteo (octavos a final)");
        System.out.println(" 2. Mostrar sorteo actual");
        System.out.println(" 3. Mostrar equipos participantes");
        System.out.println(" 4. Guardar torneo (torneo.ser)");
        System.out.println(" 5. Cargar torneo (torneo.ser)");
        System.out.println(" 6. Probar excepción: torneo con 10 equipos");
        System.out.println(" 0. Salir");
        System.out.print("Elija una opción: ");
        String linea = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1; // el controlador lo trata como opción inválida
        }
    }

    public void mostrarEquipos(Torneo torneo) {
        System.out.println();
        System.out.println("Equipos de " + torneo.getNombre() + ":");
        for (Equipo e : torneo.getEquipos()) {
            System.out.printf("  %2d. %s%n", e.getId(), e.getNombre());
        }
    }

    public void mostrarSorteo(Torneo torneo) {
        System.out.println();
        System.out.println("=== " + torneo.getNombre() + " | sorteo del " + torneo.getFechaSorteo() + " ===");
        for (Etapa etapa : torneo.getEtapas()) {
            System.out.println();
            System.out.println(">> " + etapa.getNombre());
            int n = 1;
            for (Partido p : etapa.getPartidos()) {
                System.out.printf("   Partido %d: %s%n", n++, p);
            }
        }
        System.out.println();
        System.out.println("*** CAMPEÓN: " + torneo.getCampeon() + " ***");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("[ERROR] " + mensaje);
    }

    public void cerrar() {
        scanner.close();
    }
}
