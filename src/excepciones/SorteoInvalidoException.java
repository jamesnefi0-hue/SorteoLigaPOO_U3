package excepciones;

/**
 * Excepción propia que lanza el algoritmo recursivo de sorteo cuando
 * encuentra una condición lógica imposible (equipo sin rival, equipo que
 * se enfrenta a sí mismo o equipo repetido en la misma etapa).
 */
public class SorteoInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * @param mensaje descripción del error que se envía a la superclase Exception
     */
    public SorteoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
