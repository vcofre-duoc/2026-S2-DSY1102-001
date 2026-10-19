package cl.duoc.estudiantes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import java.io.IOException;
// Maneja Stage, Scene y navegación entre FXML.
public class App extends Application {
 private static Stage stage;
 @Override public void start(Stage s)throws IOException{

  stage=s;stage.setTitle("Estudiantes DUOC");
  navegar("inicio.fxml");stage.show();

 }
 public static FXMLLoader navegar(String archivo)throws IOException{

  FXMLLoader loader=new FXMLLoader(App.class.getResource(archivo));
  Parent raiz=loader.load();Scene scene=new Scene(raiz,800,600);
  scene.getStylesheets().add(App.class.getResource("estilos.css").toExternalForm());
  stage.setScene(scene);return loader;

 }

 public static void iniciar(String[] args){launch(args);}
}
