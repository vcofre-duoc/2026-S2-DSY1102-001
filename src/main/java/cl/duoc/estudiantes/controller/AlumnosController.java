package cl.duoc.estudiantes.controller;

import cl.duoc.estudiantes.App;
import cl.duoc.estudiantes.model.*;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;


// Controller: eventos, validaciones y operaciones CRUD en memoria.
public class AlumnosController {

    //Elementos de la vista FXML
    @FXML
    private Label lblUsuario, lblEstado;
    @FXML
    private TextField txtNombre, txtConfirmar, txtEdad, txtBuscar;
    @FXML
    private ComboBox<String> cmbCarrera;
    @FXML
    private Button btnAgregar;
    @FXML
    private TableView<Estudiante> tabla;
    @FXML
    private TableColumn<Estudiante, String> colNombre, colCarrera;
    @FXML
    private TableColumn<Estudiante, Number> colEdad;

    @FXML
    private void initialize() {
        cmbCarrera.getItems().addAll("Informática", "Diseño", "Administración");
        cmbCarrera.getSelectionModel().selectFirst();

        // TextFormatter: permite solo dígitos en edad.
        txtEdad.setTextFormatter(new TextFormatter<String>(
                cambio -> cambio.getControlNewText().matches("[0-9]*") ? cambio : null
        ));

        // Binding: Agregar deshabilitado si hay campos vacíos.
        btnAgregar.disableProperty()
                .bind(Bindings.createBooleanBinding(() -> txtNombre.getText().isBlank()
                        || txtConfirmar.getText().isBlank()
                        || txtEdad.getText().isBlank(), txtNombre.textProperty(), txtConfirmar.textProperty(), txtEdad.textProperty()));

        colNombre.setCellValueFactory(c -> c.getValue().nombreProperty());
        colCarrera.setCellValueFactory(c -> c.getValue().carreraProperty());
        colEdad.setCellValueFactory(c -> c.getValue().edadProperty());

        // Editar nombre con doble clic.
        tabla.setEditable(true);
        colNombre.setCellFactory(TextFieldTableCell.forTableColumn());
        colNombre.setOnEditCommit(e -> {
            String n = e.getNewValue().trim();
            if (!n.isEmpty()) e.getRowValue().setNombre(n);
            else lblEstado.setText("Nombre no puede estar vacío.");
            tabla.refresh();
        });
        // ObservableList -> FilteredList -> SortedList -> TableView.
        FilteredList<Estudiante> filtro = new FilteredList<>(Datos.alumnos, e -> true);

        SortedList<Estudiante> orden = new SortedList<>(filtro);

        orden.comparatorProperty().bind(tabla.comparatorProperty());

        tabla.setItems(orden);

        // Listener: filtra automáticamente mientras se escribe.
        txtBuscar.textProperty().addListener((o, a, n) -> {
            String q = n.toLowerCase().trim();
            filtro.setPredicate(e -> e.getNombre().toLowerCase().contains(q)
                    || e.getCarrera().toLowerCase().contains(q));
        });

        tabla.getSelectionModel().selectedItemProperty().addListener((o, a, e) -> {
            if (e != null) lblEstado.setText("Seleccionado: " + e.getNombre());
        });

    }

    public void setUsuario(String u) {
        lblUsuario.setText("Bienvenido/a, " + u);
    }

    @FXML
    private void agregar() {
        String nombre = txtNombre.getText().trim();
        // Validación entre campos.
        if (!nombre.equals(txtConfirmar.getText().trim())) {
            error("Los nombres no coinciden.", txtConfirmar);
            return;
        }
        int edad;
        try {
            edad = Integer.parseInt(txtEdad.getText());
        } catch (NumberFormatException ex) {
            error("Edad no válida.", txtEdad);
            return;
        }
        if (edad < 1 || edad > 120) {
            error("Edad debe estar entre 1 y 120.", txtEdad);
            return;
        }
        Datos.alumnos.add(new Estudiante(nombre, cmbCarrera.getValue(), edad));
        limpiar();
        lblEstado.setText("Estudiante agregado.");
    }

    private void error(String msg, TextField campo) {
        lblEstado.setText(msg);
        if (!campo.getStyleClass().contains("campo-error")) campo.getStyleClass().add("campo-error");
        campo.requestFocus();
    }

    @FXML
    private void eliminar() {
        Estudiante e = tabla.getSelectionModel().getSelectedItem();
        if (e == null) {
            lblEstado.setText("Seleccione una fila.");
            return;
        }
        Datos.alumnos.remove(e);
        lblEstado.setText("Eliminado.");
    }

    @FXML
    private void limpiar() {
        txtNombre.clear();
        txtConfirmar.clear();
        txtEdad.clear();
        cmbCarrera.getSelectionModel().selectFirst();
        for (TextField f : new TextField[]{txtNombre, txtConfirmar, txtEdad}) f.getStyleClass().remove("campo-error");
    }

    @FXML
    private void volver() throws IOException {
        App.navegar("inicio.fxml");
    }
}
