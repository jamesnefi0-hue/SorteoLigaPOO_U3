package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa a un equipo de la liga profesional.
 */
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final String nombre;

    public Equipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipo)) return false;
        return id == ((Equipo) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa a un equipo de la liga profesional.
 */
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final String nombre;

    public Equipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipo)) return false;
        return id == ((Equipo) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
package modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa a un equipo de la liga profesional.
 */
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int id;
    private final String nombre;

    public Equipo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipo)) return false;
        return id == ((Equipo) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
