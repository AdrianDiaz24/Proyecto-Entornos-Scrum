# PRO-4.4.1-FG

**Actividad:** Documentación de Clases con KDoc y Dokka en Kotlin  
**Alumno:** Francisco Alba Muñiz
**ID Actividad:** 4.4.1  
**Proyecto:** TaskManager  
**Rama usada:** `4.4.1-FAM`  
**Ruta documentación generada:** `./build/dokka/html`

---

## Comentado del Código con KDoc

Se han documentado todas las clases del proyecto:

- datos\ `IActividadRepository.kt`, `IHistorialRepository.kt`, `IUsuarioRepository.kt`  
- dominio\ `DatosEvento.kt`, `Detallable.kt`, `Estado.kt`, `Evento.kt`, `Tarea.kt`, `Usuario.kt`
- presentacion\ `Consola.kt`
- servicios\ `Actividad.kt`, `ActividadService.kt`, `HistorialRepository.kt`, `UsuarioReposity.kt`, `UsuarioService.kt`
- .\ `Main.kt`

Los comentarios se han añadido en clases, atributos y métodos principales, usando la sintaxis oficial de KDoc `/** ... */`.

### Capturas

Nota: En este caso, para mantener este documento limpio, he optado por citar el código directamente sobre el .MD.

Algunos ejemplos de la documentación añadida:

- **Vista en IntelliJ de** `Tarea.kt`.

```kotlin
/**
 * Representa una tarea, que es una actividad con estado y posible lista de subtareas.
 * Implementa la interfaz Detallable para obtener su descripción completa.
 *
 * @property estado Estado actual de la tarea, por defecto ABIERTA.
 * @property listaSubtareas Lista mutable de subtareas asociadas.
 */
class Tarea(descripcion: String, var estado: Estado = Estado.ABIERTA) : Actividad(descripcion), Detallable {

    val listaSubtareas = mutableListOf<Tarea>()

    companion object {

        /**
         * Crea una nueva instancia de Tarea, opcionalmente con estado y etiquetas.
         *
         * @param descripcion Descripción de la tarea.
         * @param estado Estado inicial de la tarea, por defecto ABIERTA.
         * @param etiquetas Etiquetas opcionales para la tarea.
         * @return Nueva instancia de Tarea creada.
         */
        fun creaTarea(descripcion: String, estado: Estado = Estado.ABIERTA, etiquetas: String = ""): Tarea {
            val tarea = Tarea(descripcion, estado)
            if (etiquetas.isNotBlank()) {
                tarea.aniadirEtiquetas(etiquetas)
            }
            return tarea
        }
    }

    // Devuelve un String con la descripcion general de la tarea

    /**
     * Devuelve una cadena con los detalles completos de la tarea, incluyendo descripción y estado.
     *
     * @return Detalle completo de la tarea.
     */
    override fun obtenerDetalle(): String {
        return "Tarea " + super.obtenerDetalle() + " - Estado: $estado"
    }

    /**
     * Añade una subtarea a la lista de subtareas de esta tarea.
     *
     * @param tarea Subtarea a añadir.
     */
    fun aniadirSubtarea(tarea: Tarea) {
        listaSubtareas.add(tarea)
    }
}
```


- **Vista en IntelliJ de** `IHistorialRepository.kt`.

```kotlin
/**
 * Interfaz para gestionar el historial de modificaciones en el sistema.
 * Permite almacenar y registrar cambios relacionados con tareas y usuarios.
 */
interface IHistorialRepository {

    /**
     * Lista mutable que almacena las entradas del historial como cadenas de texto.
     */
    val historial: MutableList<String>

    /**
     * Añade una entrada al historial cuando se modifica el estado de una tarea.
     *
     * @param estado Nuevo estado de la tarea.
     * @param tarea La tarea que ha cambiado de estado.
     * @param contador1 Contador auxiliar (puede ser usado para referencias internas).
     * @param contador2 Contador auxiliar opcional, con valor por defecto 0.
     * @return true si la modificación se añadió correctamente, false en caso contrario.
     */
    fun añadirModificacionEstado(estado: Estado, tarea: Tarea, contador1: Int, contador2: Int = 0): Boolean

    /**
     * Añade una entrada al historial cuando se asigna un usuario a una tarea.
     *
     * @param usuario Usuario asignado a la tarea.
     * @param tarea La tarea a la que se asigna el usuario.
     * @param contador1 Contador auxiliar (uso interno).
     * @return true si la asignación se añadió correctamente, false en caso contrario.
     */
    fun añadirModificacionAsignacion(usuario: Usuario, tarea: Tarea, contador1: Int): Boolean

    /**
     * Muestra o lista el historial completo de modificaciones registradas.
     */
    fun listarHistorial()
}
```

- **Vista en IntelliJ de** `DatosEvento.kt`.

```kotlin
/**
 * Representa los datos básicos de un evento.
 *
 * @property descripcion Texto que describe el evento.
 * @property fecha Fecha en formato String (ej. "dd/mm/yyyy") del evento.
 * @property ubicacion Lugar donde se realizará el evento.
 * @property etiquetas Etiquetas o palabras clave asociadas al evento.
 */
data class DatosEvento(
    val descripcion: String,
    val fecha: String,
    val ubicacion: String,
    val etiquetas: String
)
```

---

## Configuración de Dokka

En `build.gradle.kts` se añadió:

```kotlin
plugins {
    kotlin("jvm") version "1.9.10"
    id("org.jetbrains.dokka") version "1.9.10"
}

tasks.dokkaHtml {
    outputDirectory.set(buildDir.resolve("dokka/html"))
}
```

Dependencias Dokka gestionadas automáticamente con el plugin.

---

## Generación de Documentación

### Desde IntelliJ IDEA:

Para la generación del HTML de la documentación, he seguido los siguientes pasos:

1. Abrir **Gradle Tool Window**.
2. Ejecutar la tarea: `dokka > dokkaHtml`.

### Desde la línea de comandos:

```bash
./gradlew dokkaHtml
```

Como ya lo había hecho de la segunda forma en proyectos anteriores de la asignatura de Digitalización Aplicada a los Sistemas Productivos, he decido hacerlo desde el **Gradle Tool Window**, obteniendo la siguiente salida:

```bash
16:57:24: Executing 'dokkaHtml'...


> Configure project :
w: file:///C:/Users/UsuarioT/Documents/ReposGit/Proyecto-Entornos-Scrum/build.gradle.kts:34:5: 'kotlinOptions(KotlinJvmOptionsDeprecated /* = KotlinJvmOptions */.() -> Unit): Unit' is deprecated. Please migrate to the compilerOptions DSL. More details are here: https://kotl.in/u1r8ln
w: file:///C:/Users/UsuarioT/Documents/ReposGit/Proyecto-Entornos-Scrum/build.gradle.kts:40:25: 'getter for buildDir: File!' is deprecated. Deprecated in Java
w: file:///C:/Users/UsuarioT/Documents/ReposGit/Proyecto-Entornos-Scrum/build.gradle.kts:40:25: 'getter for buildDir: File!' is deprecated. Deprecated in Java
Download https://repo.maven.apache.org/maven2/org/jetbrains/dokka/analysis-kotlin-descriptors/1.9.10/analysis-kotlin-descriptors-1.9.10.pom, took 56 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/dokka/dokka-base/1.9.10/dokka-base-1.9.10.pom, took 59 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/dokka/dokka-base/1.9.10/dokka-base-1.9.10.module, took 16 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/dokka/analysis-markdown/1.9.10/analysis-markdown-1.9.10.pom, took 15 ms
Download https://repo.maven.apache.org/maven2/org/freemarker/freemarker/2.3.31/freemarker-2.3.31.pom, took 16 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/kotlinx/kotlinx-html-jvm/0.7.5/kotlinx-html-jvm-0.7.5.pom, took 43 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/dokka/analysis-markdown/1.9.10/analysis-markdown-1.9.10.module, took 16 ms
Download https://repo.maven.apache.org/maven2/org/apache/apache/17/apache-17.pom, took 15 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/kotlinx/kotlinx-html-jvm/0.7.5/kotlinx-html-jvm-0.7.5.module, took 17 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/kotlin/kotlin-stdlib/1.9.10/kotlin-stdlib-1.9.10.pom, took 16 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core-jvm/1.6.3/kotlinx-coroutines-core-jvm-1.6.3.pom, took 17 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core-jvm/1.6.3/kotlinx-coroutines-core-jvm-1.6.3.module, took 17 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/kotlin/kotlin-stdlib-common/1.6.10/kotlin-stdlib-common-1.6.10.pom, took 16 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/kotlin/kotlin-stdlib-common/1.9.10/kotlin-stdlib-common-1.9.10.pom, took 15 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/dokka/analysis-markdown/1.9.10/analysis-markdown-1.9.10.jar, took 78 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/kotlinx/kotlinx-html-jvm/0.7.5/kotlinx-html-jvm-0.7.5.jar, took 215 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/kotlin/kotlin-stdlib-common/1.9.10/kotlin-stdlib-common-1.9.10.jar, took 259 ms
Download https://repo.maven.apache.org/maven2/org/freemarker/freemarker/2.3.31/freemarker-2.3.31.jar, took 639 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/dokka/dokka-base/1.9.10/dokka-base-1.9.10.jar, took 702 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/kotlinx/kotlinx-coroutines-core-jvm/1.6.3/kotlinx-coroutines-core-jvm-1.6.3.jar, took 881 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/kotlin/kotlin-stdlib/1.9.10/kotlin-stdlib-1.9.10.jar, took 888 ms
Download https://repo.maven.apache.org/maven2/org/jetbrains/dokka/analysis-kotlin-descriptors/1.9.10/analysis-kotlin-descriptors-1.9.10.jar, took 10 s 256 ms

> Task :dokkaHtml
Initializing plugins
Dokka is performing: documentation for PRO_2425_u4u5u6_TaskManager
Validity check
Creating documentation models
Transforming documentation model before merging
Merging documentation models
Transforming documentation model after merging
Creating pages
Transforming pages
Rendering
Running post-actions


BUILD SUCCESSFUL in 20s
1 actionable task: 1 executed
16:57:44: Execution finished 'dokkaHtml'.
```

---

## Documentación generada

- Ruta: `build/dokka/html/index.html`
- Contenido: Documentación completa del proyecto, generada a partir de los comentarios KDoc.

He desplegado un sitio web usando GitHub Pages para poder visualizar de una forma más completa toda la documentación exportada.

Nota: Como el directorio /build está excluido por .gitignore, he copiado todo el contenido de `build/dokka/html/index.html` a el directorio /docs y he seleccionado el punto de partida de la web el index.html de /docs. Como información adicional, cabe destacar que ha sido necesario ponerme en contacto con el autor del repositorio, ya que solo él puede acceder a las opciones de este.

- URL: https://adriandiaz24.github.io/Proyecto-Entornos-Scrum/

### Capturas

En este caso he optado por la opción mencionada en el punto anterior: desplegar la documentación en GitHubPages para tener una vista completa de la documentación en vez de solo una muestra de las capturas.

---

## Conclusiones

Esta actividad ha permitido documentar el proyecto TaskManager siguiendo buenas prácticas de documentación con KDoc y utilizar herramientas profesionales como Dokka para generar documentación navegable y legible.