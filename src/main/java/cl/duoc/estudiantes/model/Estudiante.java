package cl.duoc.estudiantes.model;

import javafx.beans.property.*;

// MODEL: las Properties permiten observar cambios de atributos.
public class Estudiante {
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty carrera = new SimpleStringProperty();
    private final IntegerProperty edad = new SimpleIntegerProperty();

    public Estudiante(String n, String c, int e) {
        setNombre(n);
        setCarrera(c);
        setEdad(e);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String v) {
        nombre.set(v);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public String getCarrera() {
        return carrera.get();
    }

    public void setCarrera(String v) {
        carrera.set(v);
    }

    public StringProperty carreraProperty() {
        return carrera;
    }

    public int getEdad() {
        return edad.get();
    }

    public void setEdad(int v) {
        edad.set(v);
    }

    public IntegerProperty edadProperty() {
        return edad;
    }
}
