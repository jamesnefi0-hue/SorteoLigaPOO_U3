package controlador;

import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;
import modelo.Equipo;
import modelo.GestorPersistencia;
import modelo.LigaFactory;
import modelo.Torneo;
import vista.VistaConsola;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.List;

/**
 * Controlador: recibe las acciones de la Vista, invoca al Modelo
 * (sorteo y persistencia) y devuelve los resultados a la Vista.
 */
public class ControladorTorneo {

    private final VistaConsola vista;
    private final GestorPersistencia persistencia;
    private Torneo torneo;

    public ControladorTorneo(VistaConsola vista, GestorPersistencia persistencia) {
        this.vista = vista;
        this.persistencia = persistencia;
    }

    /** Ciclo principal de la aplicación. */
    public void iniciar() {
        vista.mostrarBienvenida();
        cargarAlInicio();

        boolean salir = false;
        while (!salir) {
            int opcion = vista.pedirOpcionMenu();
            switch (opcion) {
                case 1: realizarSorteo(); break;
                case 2: mostrarSorteo(); break;
                case 3: vista.mostrarEquipos(torneo); break;
                case 4: guardarTorneo(); break;
                case 5: cargarTorneo(); break;
                case 6: probarNumeroInvalido(); break;
                case 0: salir = true; break;
                default: vista.mostrarError("Opción no válida. Intente de nuevo.");
            }
        }
        vista.mostrarMensaje("¡Hasta pronto!");
        vista.cerrar();
    }

    /** Al iniciar intenta recuperar el torneo guardado; si no existe, crea uno nuevo. */
    private void cargarAlInicio() {
        try {
            torneo = persistencia.cargar();
            vista.mostrarMensaje("Se cargó el torneo guardado en " + persistencia.getRutaArchivo());
        } catch (FileNotFoundException e) {
            vista.mostrarMensaje("No hay torneo guardado. Se crea uno nuevo con 16 equipos.");
            crearTorneoNuevo();
        } catch (IOException | ClassNotFoundException e) {
            vista.mostrarError("No se pudo leer el archivo guardado (" + e.getMessage()
                    + "). Se crea un torneo nuevo.");
            crearTorneoNuevo();
        }
    }

    private void crearTorneoNuevo() {
        try {
            torneo = new Torneo("Liga Pro 2026", LigaFactory.crearEquiposIniciales());
        } catch (NumeroEquiposInvalidoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

    private void realizarSorteo() {
        try {
            torneo.realizarSorteo();
            vista.mostrarSorteo(torneo);
        } catch (NumeroEquiposInvalidoException e) {
            vista.mostrarError("Número de equipos inválido: " + e.getMessage());
        } catch (SorteoInvalidoException e) {
            vista.mostrarError("Sorteo inválido: " + e.getMessage());
        }
    }

    private void mostrarSorteo() {
        if (torneo.tieneSorteo()) {
            vista.mostrarSorteo(torneo);
        } else {
            vista.mostrarMensaje("Todavía no se ha realizado el sorteo (opción 1).");
        }
    }

    private void guardarTorneo() {
        try {
            persistencia.guardar(torneo);
            vista.mostrarMensaje("Torneo guardado correctamente en " + persistencia.getRutaArchivo());
        } catch (IOException e) {
            vista.mostrarError("No se pudo guardar el torneo: " + e.getMessage());
        }
    }

    private void cargarTorneo() {
        try {
            torneo = persistencia.cargar();
            vista.mostrarMensaje("Torneo cargado correctamente.");
            mostrarSorteo();
        } catch (FileNotFoundException e) {
            vista.mostrarError("No existe el archivo " + persistencia.getRutaArchivo()
                    + ". Guarde primero el torneo (opción 4).");
        } catch (InvalidClassException e) {
            vista.mostrarError("El archivo fue creado con otra versión del programa.");
        } catch (IOException e) {
            vista.mostrarError("Error de lectura del archivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            vista.mostrarError("No se encontró la clase del objeto guardado: " + e.getMessage());
        }
    }

    /** Demuestra que la excepción propia se lanza y se captura correctamente. */
    private void probarNumeroInvalido() {
        try {
            List<Equipo> diezEquipos = LigaFactory.crearEquiposIniciales().subList(0, 10);
            new Torneo("Torneo de prueba", diezEquipos);
            vista.mostrarMensaje("El torneo de prueba se creó (no debería ocurrir).");
        } catch (NumeroEquiposInvalidoException e) {
            vista.mostrarError("Excepción capturada correctamente -> " + e.getMessage());
        }
    }
}
