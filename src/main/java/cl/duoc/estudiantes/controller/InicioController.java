package cl.duoc.estudiantes.controller;

import cl.duoc.estudiantes.App;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

// Lee el usuario y pasa el dato al Controller de la segunda vista.
public class InicioController {

    @FXML
    private TextField txtUsuario;
    @FXML
    private Label lblMensaje;

    @FXML
    private void ingresar() throws IOException {

        String nombre = txtUsuario.getText().trim();
        if (nombre.isEmpty()) {
            lblMensaje.setText("Ingrese su nombre.");
            return;
        }
        var loader = App.navegar("alumnos.fxml");

        AlumnosController destino = loader.getController();
        destino.setUsuario(nombre);

    }
}
