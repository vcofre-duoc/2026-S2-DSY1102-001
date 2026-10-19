package cl.duoc.estudiantes.model;

import javafx.collections.*;

// ObservableList: notifica adiciones y eliminaciones a TableView.
public class Datos {
    public static final ObservableList<Estudiante> alumnos = FXCollections.observableArrayList();

    private Datos() {
    }
}
