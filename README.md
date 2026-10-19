# Gestión de Estudiantes — JavaFX MVC

**DSY1102 · Java · Maven · IntelliJ IDEA**

## ¿Qué hace?

Dos pantallas: bienvenida y gestión de estudiantes. Permite agregar, listar, seleccionar, editar el nombre con doble clic, eliminar, filtrar por nombre o carrera y ordenar haciendo clic en encabezados. Los datos permanecen en memoria mientras la aplicación esté abierta.

## Contenido

| PPT | Concepto | Archivo o método |
|---|---|---|
| 2.3.1 | MVC | `model/Estudiante`, `inicio.fxml`, `alumnos.fxml`, `InicioController`, `AlumnosController` |
| 2.3.1 | Properties / Binding | `Estudiante.java`, `AlumnosController.initialize()` |
| 2.3.2 | ObservableList / TableView / TableColumn | `Datos.alumnos`, `AlumnosController.initialize()` |
| 2.3.2 | Selección, edición, filtrado y orden | `AlumnosController.initialize()`, `eliminar()` |
| 2.3.3 | Stage / Scene / FXMLLoader | `App.navegar()` |
| 2.3.3 | Paso de datos entre Controllers | `InicioController.ingresar()`, `AlumnosController.setUsuario()` |
| 2.3.4 | TextFormatter, Listener, Binding | `AlumnosController.initialize()` |
| 2.3.4 | Validación entre campos, rango, mensajes, CSS | `agregar()`, `error()`, `estilos.css` |

## Orden sugerido para construirlo en clases

1. Crear proyecto Maven con JDK 17 o superior y configurar `pom.xml`.
2. Crear `Main.java` como punto de entrada y `App.java` para JavaFX.
3. Crear el modelo `Estudiante` con Properties y getters/setters.
4. Crear `Datos` con `ObservableList<Estudiante>`.
5. Diseñar `inicio.fxml` y `alumnos.fxml` con Scene Builder o manualmente.
6. Crear `InicioController` y conectar `fx:id`, `fx:controller` y `onAction`.
7. Crear `AlumnosController` y cargar carreras en `initialize()`.
8. Configurar `TableColumn` y conectar la `ObservableList` al `TableView`.
9. Programar Agregar, Eliminar y la edición de nombre en la tabla.
10. Incorporar `FilteredList`, `SortedList` y Listener de búsqueda.
11. Crear navegación entre vistas y pasar el nombre del usuario.
12. Añadir `TextFormatter`, Binding, validaciones y feedback CSS.
13. Probar los flujos de registro, búsqueda, edición, eliminación y retorno.

## Ejecutar en IntelliJ

1. Clonar Repositorio y abrir la carpeta con **File > Open**.
2. Esperar que Maven descargue las dependencias (requiere Internet la primera vez).
3. Seleccionar JDK 17 o superior.
4. Ejecutar `cl.duoc.estudiantes.Main`. También se puede usar `mvn javafx:run`.

## Pruebas recomendadas

- Intentar entrar sin nombre y después ingresar uno válido.
- Registrar un estudiante con edad 20 y nombre confirmado.
- Intentar edades 0 y 121, y nombres diferentes entre ambos campos.
- Filtrar por nombre y carrera; ordenar pulsando una columna.
- Seleccionar y eliminar; editar el nombre con doble clic.
- Volver a Inicio y regresar: los estudiantes siguen en memoria.

**Sin base de datos, sin servicios externos y sin frameworks adicionales.**
