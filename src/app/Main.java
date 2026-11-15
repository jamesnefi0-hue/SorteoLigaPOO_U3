package app;

import controlador.ControladorTorneo;
import modelo.GestorPersistencia;
import vista.VistaConsola;

/**
 * Punto de entrada: arma las piezas del MVC y arranca la aplicación.
 */
public class Main {
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        GestorPersistencia persistencia = new GestorPersistencia();
        ControladorTorneo controlador = new ControladorTorneo(vista, persistencia);
        controlador.iniciar();
    }
}
