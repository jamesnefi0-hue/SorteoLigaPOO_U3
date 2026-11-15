package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Responsable de la persistencia del torneo mediante serialización de objetos.
 */
public class GestorPersistencia {

    public static final String ARCHIVO_POR_DEFECTO = "torneo.ser";

    private final String rutaArchivo;

    public GestorPersistencia() {
        this(ARCHIVO_POR_DEFECTO);
    }

    public GestorPersistencia(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Serializa el torneo en un archivo binario usando
     * ObjectOutputStream + FileOutputStream.
     */
    public void guardar(Torneo torneo) throws IOException {
        // try-with-resources cierra los flujos automáticamente
        try (ObjectOutputStream salida =
                     new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            salida.writeObject(torneo);
        }
    }

    /**
     * Deserializa el torneo desde el archivo binario usando
     * ObjectInputStream + FileInputStream.
     */
    public Torneo cargar() throws IOException, ClassNotFoundException {
        try (ObjectInputStream entrada =
                     new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            return (Torneo) entrada.readObject();
        }
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }
}
